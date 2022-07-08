package com.bunty.roomnotesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("SELECT * FROM notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>

}