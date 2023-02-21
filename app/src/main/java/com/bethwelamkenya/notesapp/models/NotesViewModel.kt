package com.bethwelamkenya.notesapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bethwelamkenya.notesapp.database.notes.NotesDatabase
import com.bethwelamkenya.notesapp.database.notes.NotesRepository
import io.reactivex.annotations.SchedulerSupport.IO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class NotesViewModel(application: Application, order: String) : AndroidViewModel(application) {
    private val repository: NotesRepository
    val allNotes: LiveData<List<Note>>
    init {
        val dao = NotesDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao, order)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

}