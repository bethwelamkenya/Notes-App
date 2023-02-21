package com.bethwelamkenya.notesapp.interfaces.note

import androidx.cardview.widget.CardView
import com.bethwelamkenya.notesapp.models.Notes

interface NoteClicked {
    fun onNoteClicked(notes: Notes)
    fun onNoteLongClicked(notes: Notes, cardView: CardView)
}