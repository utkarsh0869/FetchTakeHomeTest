package com.example.fetchtakehometest.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchtakehometest.network.Item
import com.example.fetchtakehometest.repository.ItemsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    private var _groupedItems by mutableStateOf<Map<Int, List<Item>>>(emptyMap())
    val groupedItems: Map<Int, List<Item>>
        get() = _groupedItems

    init {
        getItemsProperties()
    }

    private fun getItemsProperties() {
        viewModelScope.launch {
            val response = try {
                itemsRepository.getItems()
            } catch (e: IOException) {
                Log.e("ItemsViewModel", "IOException, maybe internet connection is not established.")
                return@launch
            } catch (e: HttpException) {
                Log.e("ItemsViewModel", "HttpException, unexpected response.")
                return@launch
            }
            processResponse(response)
        }
    }

    private fun processResponse(response: List<Item>?) {
        response?.let { listOfItem ->
            _groupedItems = listOfItem.filter { item ->
                item.name?.isNotEmpty() == true
            }.sortedWith(
                compareBy( { it.listId }, { it.name })
            ).groupBy {item ->
                item.listId
            }
        } ?: Log.e("ItemsViewModel", "Response not successful.")
    }
}