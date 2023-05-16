package com.mongodb.talktome

import android.app.Application
import android.util.Log
import com.mongodb.talktome.model.Talk
import io.realm.kotlin.Realm
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.runBlocking

class TalkToMeApp : Application() {
    lateinit var realm: Realm

    companion object {
        const val APP_ID = "<app-id>"
        const val SYNC_QUERY = "TALK_QUERY"
    }

    override fun onCreate() {
        super.onCreate()
        val app = App.create(APP_ID)
        runBlocking {
            val user = app.login(Credentials.anonymous())
            val config = SyncConfiguration.Builder(user = user, schema = setOf(Talk::class))
                .log(LogLevel.DEBUG)
                .initialSubscriptions { realm ->
                    add(
                        query = realm.query<Talk>(
                            Talk::class,
                            "ownerId == $0",
                            user.id
                        ),
                        name = SYNC_QUERY
                    )
                }
                .waitForInitialRemoteData()
                .build()
            realm = Realm.open(config)
            Log.v("Realm", "Successfully opened realm: ${realm.configuration}")
        }
    }
}
