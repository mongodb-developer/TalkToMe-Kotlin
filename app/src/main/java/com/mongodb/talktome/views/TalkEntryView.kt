package com.mongodb.talktome.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.mongodb.talktome.viewmodels.TalkEntryViewModel

@Composable
fun TalkEntryView(viewModel: TalkEntryViewModel, dialogState: MutableState<Boolean>) {
    val titleField = remember { mutableStateOf("") }
    val speakerField = remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Talk",
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(
                    modifier = Modifier.then(Modifier.size(24.dp)),
                    onClick = {
                        dialogState.value = false
                    }
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                text = titleField,
                placeholder = "Talk title",
                leadingImage = Icons.Filled.List
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                text = speakerField,
                placeholder = "Speaker Name",
                leadingImage = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    viewModel.confirmData(name = speakerField.value, title = titleField.value)
                    dialogState.value = false
                },
                modifier = Modifier.align(Alignment.End),
                enabled = titleField.value.isNotEmpty() && speakerField.value.isNotEmpty()
            ) {
                Text(text = "Done")
            }
        }
    }
}

@Composable
fun CustomTextField(text: MutableState<String>, placeholder: String, leadingImage: ImageVector) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = leadingImage,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        placeholder = { Text(text = placeholder) },
        value = text.value,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
        onValueChange = {
            text.value = it
        }
    )
}
