@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fetchtakehometest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchtakehometest.network.Item
import com.example.fetchtakehometest.repository.ItemsRepositoryImpl
import com.example.fetchtakehometest.ui.theme.FetchTakeHomeTestTheme
import com.example.fetchtakehometest.viewmodel.ItemsViewModel
import com.example.fetchtakehometest.viewmodel.ItemsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTakeHomeTestTheme {
                val itemsViewModel = viewModel<ItemsViewModel>(
                    factory = ItemsViewModelFactory(ItemsRepositoryImpl())
                )
                val groupedItems = itemsViewModel.groupedItems
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemsComposable(
                        modifier = Modifier.padding(innerPadding),
                        groupedItems = groupedItems
                    )
                }
            }
        }
    }
}
@Composable
fun ItemsComposable(modifier: Modifier, groupedItems: Map<Int, List<Item>>) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        groupedItems.forEach {
            stickyHeader {
                CategoryHeader(listId = it.key)
            }
            items(it.value) {item ->
                CategoryItem(id = item.id, name = item.name)
            }
        }
    }
}

@Composable
fun CategoryHeader(
    listId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = listId.toString(),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
fun CategoryItem(
    id: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Item name = $name. ID = $id.",
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}