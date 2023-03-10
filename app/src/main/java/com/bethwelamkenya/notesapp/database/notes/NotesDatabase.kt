package com.bethwelamkenya.notesapp.database.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bethwelamkenya.notesapp.models.Note
import com.bethwelamkenya.notesapp.utilities.DATABASE_NAME

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context) : NotesDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, DATABASE_NAME).build()
                INSTANCE = instance
                instance
            }
        }
    }
}