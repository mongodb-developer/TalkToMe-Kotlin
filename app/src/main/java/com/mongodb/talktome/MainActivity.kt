package com.mongodb.talktome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mongodb.talktome.model.Talk
import com.mongodb.talktome.ui.theme.TalkToMeTheme
import com.mongodb.talktome.viewmodels.TalksListViewModel
import com.mongodb.talktome.views.TalksListView
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = RealmConfiguration.Builder(schema = setOf(Talk::class))
            .deleteRealmIfMigrationNeeded()
            .build()
        val realm: Realm = Realm.open(config)
        val viewModel = TalksListViewModel(realm = realm)

        setContent {
            TalkToMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TalksListView(viewModel = viewModel)
                }
            }
        }
    }
}
