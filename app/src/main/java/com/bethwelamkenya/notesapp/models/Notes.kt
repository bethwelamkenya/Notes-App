package com.bethwelamkenya.notesapp.models

data class Notes(
    val id: Long,
    val title: String,
    val text: String,
    val date: String,
    val color: Int
) {
    constructor(title: String, text: String, date: String, color: Int) : this(0, title, text, date, color)
}
