package com.bethwelamkenya.notesapp.fragments.notes

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.adapters.notes.NotesRecyclerViewAdapter
import com.bethwelamkenya.notesapp.database.notes.NotesDatabase
import com.bethwelamkenya.notesapp.interfaces.notes.NotesOnClick
import com.bethwelamkenya.notesapp.interfaces.notes.PassNoteData
import com.bethwelamkenya.notesapp.models.Note
import com.bethwelamkenya.notesapp.models.NotesViewModel
import com.bethwelamkenya.notesapp.ui.MainActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class NotesHomeFragment : Fragment() , NotesOnClick {

    private lateinit var addNote: ExtendedFloatingActionButton
    private val passNoteData = PassNoteData()
    private lateinit var adapter: NotesDatabase
    private lateinit var recyclerAdapter: NotesRecyclerViewAdapter
    private lateinit var selectedNote: Note
    private lateinit var viewModel: NotesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainActivity: Activity
    private lateinit var notes: ArrayList<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes_home, container, false)
        mainActivity = MainActivity()
        addNote = view.findViewById(R.id.addNote)
        recyclerView = view.findViewById(R.id.notesRecyclerView)
        adapter = NotesDatabase.getDatabase(this.requireActivity())

        initView()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(mainActivity.application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(viewLifecycleOwner) { list ->
            list?.let {
                recyclerAdapter.updateAdapter(list)
            }
        }
        addNote.setOnClickListener { findNavController().navigate(R.id.action_notesHomeFragment_to_notesAddEditFragment) }
        return view
    }

    private fun initView() {
        recyclerAdapter = NotesRecyclerViewAdapter(mainActivity, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        recyclerView.adapter = recyclerAdapter
    }

    fun saveNote(note: Note){
        viewModel.insertNote(note)
    }

    fun editNote(note: Note){
        viewModel.updateNote(note)
    }

    override fun onNoteClicked(note: Note) {
        passNoteData.note = note
        findNavController().navigate(R.id.action_notesHomeFragment_to_notesAddEditFragment)
    }

    override fun onNoteLongClicked(note: Note, cardView: CardView) {

    }
}