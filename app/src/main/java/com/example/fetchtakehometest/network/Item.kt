package com.example.fetchtakehometest.network

import java.io.Serializable

data class Item(val id: Int, val listId: Int, val name: String) : Serializable {}
