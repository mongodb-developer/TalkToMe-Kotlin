package com.mongodb.talktome.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject

class Talk() : RealmObject {
    var title: String = ""
    var speaker: String = ""
    private var proposedDate: RealmInstant = RealmInstant.now()
    var scheduledDate: RealmInstant? = null

    constructor(title: String, speaker: String) : this() {
        this.title = title
        this.speaker = speaker
    }
}
