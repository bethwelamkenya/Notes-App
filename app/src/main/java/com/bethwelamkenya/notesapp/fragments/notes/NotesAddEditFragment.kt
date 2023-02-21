package com.bethwelamkenya.notesapp.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.database.notes.NotesDatabase
import com.bethwelamkenya.notesapp.interfaces.notes.PassNoteData
import com.bethwelamkenya.notesapp.models.Note
import com.bethwelamkenya.notesapp.ui.MainActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class NotesAddEditFragment : Fragment() {

    private lateinit var saveNote: ExtendedFloatingActionButton
    private lateinit var cancel: ExtendedFloatingActionButton
    private lateinit var title: EditText
    private lateinit var text: EditText
    private val passNoteData = PassNoteData()
    private lateinit var note: Note
    private var isEditing = false
    private lateinit var adapter: NotesDatabase
    private lateinit var mainActivity: MainActivity
    private lateinit var notesHomeFragment: NotesHomeFragment
    private val formatter = SimpleDateFormat("EEE, dd/MM/yyyy HH:mm a")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes_add_edit, container, false)
        mainActivity = MainActivity()
        adapter = NotesDatabase.getDatabase(this.requireContext())
        notesHomeFragment = NotesHomeFragment()
        saveNote = view.findViewById(R.id.saveNote)
        cancel = view.findViewById(R.id.cancel)
        title = view.findViewById(R.id.noteTitles)
        text = view.findViewById(R.id.noteTexts)
        if (passNoteData.note == null){
            isEditing = false
        } else{
            isEditing = true
            note = passNoteData.note!!
            title.setText(note.noteTitle)
            text.setText(note.noteText)
        }
        saveNote.setOnClickListener {
            if (isEditing){
                editNote()
            }else{
                saveNote()
            }
        }
        cancel.setOnClickListener { findNavController().navigate(R.id.action_notesAddEditFragment_to_notesHomeFragment) }
        return view
    }

    private fun saveNote(){
        try {
            val note = Note(0, title.text.toString(), text.text.toString(), formatter.format(Date()), 0)
            notesHomeFragment.saveNote(note)
            passNoteData.note = null
            findNavController().navigate(R.id.action_notesAddEditFragment_to_notesHomeFragment)
        } catch (ex: Exception){
            println(ex.message)
        }
    }

    private fun editNote(){
        try {
            val note = Note(note.id, title.text.toString(), text.text.toString(), note.noteDate, 0)
            notesHomeFragment.editNote(note)
            passNoteData.note = null
            findNavController().navigate(R.id.action_notesAddEditFragment_to_notesHomeFragment)
        } catch (ex: Exception){
            println(ex.message)
        }
    }
}