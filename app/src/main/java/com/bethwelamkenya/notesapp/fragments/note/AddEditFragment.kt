package com.bethwelamkenya.notesapp.fragments.note

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.database.note.NotesDatabaseAdapter
import com.bethwelamkenya.notesapp.interfaces.note.PassData
import com.bethwelamkenya.notesapp.interfaces.note.PassNoteFromActivity
import com.bethwelamkenya.notesapp.models.Notes
import com.bethwelamkenya.notesapp.ui.NotesActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class AddEditFragment : Fragment() ,PassNoteFromActivity{

    private lateinit var saveNote: ExtendedFloatingActionButton
    private lateinit var cancel: ExtendedFloatingActionButton
    private lateinit var title: EditText
    private lateinit var text: EditText
    private lateinit var adapter: NotesDatabaseAdapter
    private lateinit var notesActivity: Activity
    private lateinit var homeFragment: HomeFragment
    private val passData =  PassData()
    var theNote: Notes? = null
    private var isEditing = false
    private val formatter = SimpleDateFormat("EEE, dd/MM/yyyy HH:mm a")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)
        notesActivity = NotesActivity()
        homeFragment = HomeFragment()
        adapter = NotesDatabaseAdapter(this.requireContext())
        saveNote = view.findViewById(R.id.saveNote)
        cancel = view.findViewById(R.id.cancel)
        title = view.findViewById(R.id.noteTitles)
        text = view.findViewById(R.id.noteTexts)
        println("hi")
        try {
            theNote = passData.notes
            println(theNote!!.title)
            println(theNote!!.text)
            title.setText(theNote!!.title)
            text.setText(theNote!!.text)
        } catch (e: Exception){
            println("the error has been: " + e.message)
        }
        println(isEditing)
        cancel.setOnClickListener { findNavController().navigate(R.id.action_addEditFragment_to_homeFragment) }
        saveNote.setOnClickListener {
            if (isEditing){
                editNote()
            } else{
                saveNote()
            }
        }
        return view
    }

    private fun saveNote(){
        try {
            val notes = Notes(
                title.text.toString(),
                text.text.toString(),
                formatter.format(Date()),
                0
            )
            adapter.insert(notes)
            findNavController().navigate(R.id.action_addEditFragment_to_homeFragment)
        } catch (e: Exception){
            println(e.message)
        }
    }

    private fun editNote(){
        try {
            val notes = Notes(
                theNote!!.id,
                title.text.toString(),
                text.text.toString(),
                theNote!!.date,
                0
            )
            adapter.update(notes)
            findNavController().navigate(R.id.action_addEditFragment_to_homeFragment)
        } catch (e: Exception){
            println(e.message)
        }
    }

    fun passeNote(note: Notes) {
        isEditing = true
        theNote = note
        println(note.title)
        println(note.text)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
    }

    override fun passNote(note: Notes) {
        isEditing = true
        theNote = note
        passData.notes = note
        println(note.title)
        println(note.text)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
        println(isEditing)
    }

}