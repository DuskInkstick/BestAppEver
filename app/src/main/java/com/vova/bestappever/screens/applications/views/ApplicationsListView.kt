package com.vova.bestappever.screens.applications.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vova.bestappever.data.models.Application

@Composable
fun ApplicationsListView(
    title: String,
    apps: List<Application>,
    modifier: Modifier = Modifier,
    onAppClick: (index: Int, app: Application) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 24.sp)
        HorizontalDivider(modifier = Modifier.padding(8.dp))

        LazyColumn {
            itemsIndexed(apps) { i, app ->
                ApplicationBriefCard(
                    app = app,
                    modifier = Modifier.padding(8.dp, 16.dp, 8.dp, 0.dp)
                ) {
                    onAppClick(i, app)
                }
            }
        }
    }
}