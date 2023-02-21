package com.bethwelamkenya.notesapp.database.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bethwelamkenya.notesapp.models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from notes_table order by :order asc")
    fun getNotes(order: String) : LiveData<List<Note>>

    @Query("select * from notes_table where id = :id")
    fun getNote(id: Long) : LiveData<Note>

    @Query("update notes_table set note_title = :noteTitle, note_text = :noteText, note_color = :noteColor where id = :id")
    suspend fun updateNote(id: Long, noteTitle: String, noteText: String, noteColor: Int)
}