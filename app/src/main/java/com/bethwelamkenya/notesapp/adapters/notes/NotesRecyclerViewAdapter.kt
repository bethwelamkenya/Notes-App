package com.bethwelamkenya.notesapp.adapters.notes

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.interfaces.notes.NotesOnClick
import com.bethwelamkenya.notesapp.models.Note
import java.util.*

class NotesRecyclerViewAdapter(private val context: Context, private val notesOnClick: NotesOnClick) :
    RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesRecyclerViewHolder>() {

    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRecyclerViewHolder {
        return NotesRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_view, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotesRecyclerViewHolder, position: Int) {
//        val currentNote = notesList.value?.get(position)
        val currentNote = notesList[position]
        holder.noteTitle.text = currentNote.noteTitle
        holder.noteText.text = currentNote.noteText
        holder.noteDate.text = currentNote.noteDate
        when(currentNote.noteColor){
            1 -> {
                holder.cardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.colorPrimary, null))
            }
            2 -> {
                holder.cardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.amber_A400, null))
            }
            3 -> {
                holder.cardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.stem, null))
            }
            else -> {
                holder.cardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.main, null))
            }
        }

        holder.cardLayout.setOnClickListener{ notesOnClick.onNoteClicked(currentNote) }
        holder.cardLayout.setOnLongClickListener {
            notesOnClick.onNoteLongClicked(currentNote, holder.cardLayout)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateAdapter(list: List<Note>){
        fullList.clear()
        notesList.clear()
        fullList.addAll(list)
        notesList.addAll(list)
        notifyDataSetChanged()
    }

    fun filterList(search: String){
        notesList.clear()
        for (note in fullList){
            if (note.noteTitle.lowercase(Locale.ROOT).contains(search.lowercase(Locale.ROOT)) ||
                note.noteText.lowercase(Locale.ROOT).contains(search.lowercase(Locale.ROOT))){
                notesList.add(note)
            }
        }
        notifyDataSetChanged()
    }

    inner class NotesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainLayout = itemView.findViewById<ConstraintLayout>(R.id.notesMainLayout)
        val cardLayout = itemView.findViewById<CardView>(R.id.notesCardView)
        val noteTitle = itemView.findViewById<TextView>(R.id.noteTitle)
        val noteText = itemView.findViewById<TextView>(R.id.noteText)
        val noteDate = itemView.findViewById<TextView>(R.id.noteDate)
        val editNote = itemView.findViewById<ImageButton>(R.id.editNoteImage)
        val deleteNote = itemView.findViewById<ImageButton>(R.id.deleteNoteImage)
    }

}