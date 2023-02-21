package com.bethwelamkenya.notesapp.database.note

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.bethwelamkenya.notesapp.models.Notes

class NotesDatabaseAdapter(context: Context) : SQLiteOpenHelper(context, database_name, null, database_version) {
    companion object{
        private const val database_name = "notes.db"
        private const val database_version = 1
        private const val table = "notes_table"
        private const val id = "id"
        private const val title = "title"
        private const val text = "text"
        private const val date = "date"
        private const val color = "color"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("create table $table ($id integer primary key autoincrement, " +
                "$title text not null, " +
                "$text text, " +
                "$date text," +
                "$color integer)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $table")
        onCreate(db)
    }

    fun insert(note: Notes) : Long{
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(title, note.title)
        contentValue.put(text, note.text)
        contentValue.put(date, note.date)
        contentValue.put(color, note.color)
        val result = db.insert(table, null, contentValue)
        db.close()
        return result
    }

    fun update(note: Notes) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(id, note.id)
        contentValues.put(title, note.title)
        contentValues.put(text, note.text)
        contentValues.put(date, note.date)
        contentValues.put(color, note.color)
        val result = db.update(table, contentValues, "$id=" + note.id, null)
        db.close()
        return result
    }

    @SuppressLint("Range")
    fun getNotes(): ArrayList<Notes>? {
        val notes: ArrayList<Notes> = ArrayList()
        val query = "select * from $table"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(query, null)
        } catch (ex: SQLiteException){
            println(ex.message)
            db.execSQL(query)
            return null
        }
        while (cursor.moveToNext()){
            notes.add(
                Notes(cursor.getLong(cursor.getColumnIndex(id)),
                    cursor.getString(cursor.getColumnIndex(title)),
                    cursor.getString(cursor.getColumnIndex(text)),
                    cursor.getString(cursor.getColumnIndex(date)),
                    cursor.getInt(cursor.getColumnIndex(color))
                )
            )
        }
        cursor.close()
        return notes
    }

    fun delete(theId: Long) : Int{
        val db = this.writableDatabase
        val result = db.delete(table, "$id=$theId", null)
        db.close()
        return result
    }
}