package com.bethwelamkenya.notesapp.interfaces.notes

import androidx.cardview.widget.CardView
import com.bethwelamkenya.notesapp.models.Note

interface NotesOnClick {
    fun onNoteClicked(note: Note)
    fun onNoteLongClicked(note: Note, cardView: CardView)
}