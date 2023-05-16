package com.mongodb.talktome.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mongodb.talktome.model.Talk
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.syncSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TalkEntryViewModel(private var realm: Realm) : ViewModel() {
    fun confirmData(name: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write {
                copyToRealm(
                    Talk(title = title, speaker = name, ownerId = realm.syncSession.user.id)
                )
            }
        }
    }
}
