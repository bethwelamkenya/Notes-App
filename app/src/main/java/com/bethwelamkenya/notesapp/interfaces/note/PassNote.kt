package com.bethwelamkenya.notesapp.interfaces.note

import com.bethwelamkenya.notesapp.models.Notes

interface PassNote {
    fun passedNote(note: Notes)
}