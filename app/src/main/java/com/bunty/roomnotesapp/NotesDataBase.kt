package com.bunty.roomnotesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDataBase? = null

        fun getDatabase(context: Context): NotesDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NotesDataBase::class.java,
                        "notes_database"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}