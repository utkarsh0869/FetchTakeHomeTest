package com.example.fetchtakehometest.repository

import com.example.fetchtakehometest.network.Item
import com.example.fetchtakehometest.network.ItemsApi
import retrofit2.Response

interface ItemsRepository {
    suspend fun getItems(): List<Item>?
}

class ItemsRepositoryImpl : ItemsRepository {
    override suspend fun getItems(): List<Item>? {
        val response: Response<List<Item>> = ItemsApi.retrofitService.getItemsProperties()
        return if (response.isSuccessful) response.body() else null
    }
}