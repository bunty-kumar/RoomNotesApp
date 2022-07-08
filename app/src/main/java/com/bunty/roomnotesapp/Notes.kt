package com.bunty.roomnotesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Notes(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "notesDesc") val noteDesc: String,
    @ColumnInfo(name = "timeStamp") val noteTimeStamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}