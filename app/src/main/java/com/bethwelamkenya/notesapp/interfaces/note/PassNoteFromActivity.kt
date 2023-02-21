package com.bethwelamkenya.notesapp.interfaces.note

import com.bethwelamkenya.notesapp.models.Notes

interface PassNoteFromActivity {
    fun passNote(note: Notes)
}