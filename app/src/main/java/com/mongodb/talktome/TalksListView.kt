package com.mongodb.talktome

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mongodb.talktome.ui.theme.TalkToMeTheme

@Composable
fun TalksListView(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TalksListViewPreview() {
    TalkToMeTheme {
        TalksListView("Android")
    }
}
