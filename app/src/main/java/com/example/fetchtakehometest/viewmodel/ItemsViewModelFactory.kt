package com.example.fetchtakehometest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchtakehometest.repository.ItemsRepository

class ItemsViewModelFactory(
    private val itemsRepository: ItemsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsRepository) as T
    }
}