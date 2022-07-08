package com.bunty.roomnotesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(notes: Notes) {
        notesDao.insertNote(notes)
    }

    suspend fun update(notes: Notes) {
        notesDao.updateNotes(notes)
    }

    suspend fun delete(notes: Notes) {
        notesDao.deleteNotes(notes)
    }

}