@file:OptIn(ExperimentalMaterial3Api::class)

package com.mongodb.talktome.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mongodb.talktome.R
import com.mongodb.talktome.viewmodels.TalksListViewModel

@Composable
fun TalksListView(viewModel: TalksListViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addButtonTapped()
            }) {
                Icon(Icons.Filled.Add, "Add talks")
            }
        }
    ) { innerPadding ->
        val talks by viewModel.talks.observeAsState(emptyList())
        LazyColumn(contentPadding = innerPadding) {
            items(items = talks, key = { talk -> talk._id.toHexString() }) { talk ->
                val currentTalk = rememberUpdatedState(newValue = talk)
                val dismissState = rememberDismissState(confirmValueChange = {
                    if (it == DismissValue.DismissedToStart) {
                        viewModel.removeTalk(currentTalk.value)
                        true
                    } else {
                        false
                    }
                })
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val direction = dismissState.dismissDirection ?: {}

                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.DismissedToStart -> Color.Red
                                else -> Color.White
                            }
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp)
                        ) {
                            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                                if (direction == DismissDirection.EndToStart) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    },
                    dismissContent = {
                        TalksListItem(title = talk.title, speaker = talk.speaker)
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
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
