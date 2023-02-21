package com.bethwelamkenya.notesapp.database.notes

import androidx.lifecycle.LiveData
import com.bethwelamkenya.notesapp.models.Note

class NotesRepository(private val noteDao: NoteDao, order: String) {
    val allNotes: LiveData<List<Note>> = noteDao.getNotes(order)

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note.id, note.noteTitle, note.noteText, note.noteColor)
    }
}