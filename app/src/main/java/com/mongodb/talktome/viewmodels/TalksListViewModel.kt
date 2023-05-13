package com.mongodb.talktome.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mongodb.talktome.model.Talk
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TalksListViewModel(val realm: Realm) : ViewModel() {
    val talks: LiveData<List<Talk>> = getAllTalks().map { it.list }.asLiveData()

    private fun getAllTalks(): Flow<ResultsChange<Talk>> = realm.query<Talk>().asFlow()

    fun removeTalk(talk: Talk) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write {
                findLatest(talk)?.also {
                    delete(it)
                }
            }
        }
    }
}
