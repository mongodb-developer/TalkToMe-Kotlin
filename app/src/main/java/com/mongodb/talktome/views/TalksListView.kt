@file:OptIn(ExperimentalMaterial3Api::class)

package com.mongodb.talktome.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mongodb.talktome.R
import com.mongodb.talktome.ui.theme.TalkToMeTheme

@Composable
fun TalksListView(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Action not defined yet
            }) {
                Icon(Icons.Filled.Add, "Add talks")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item {
                TalksListItem(title = "Growing Hyperpotatoes", speaker = "Captn. Hype")
                Divider()
            }
            item {
                TalksListItem(title = "The wild mushrooms rebellion", speaker = "Toad")
                Divider()
            }
        }
    }
}

@Composable
fun TalksListItem(title: String, speaker: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = speaker,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun TalksListViewPreview() {
    TalkToMeTheme {
        TalksListView()
    }
}
