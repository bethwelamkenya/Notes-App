package com.bethwelamkenya.notesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "note_title")val noteTitle: String,
    @ColumnInfo(name = "note_text")val noteText: String,
    @ColumnInfo(name = "note_date")val noteDate: String,
    @ColumnInfo(name = "note_color")val noteColor: Int
) : java.io.Serializable
