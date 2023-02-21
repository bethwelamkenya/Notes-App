package com.bethwelamkenya.notesapp.fragments.note

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.adapters.note.NotesAdapter
import com.bethwelamkenya.notesapp.database.note.NotesDatabaseAdapter
import com.bethwelamkenya.notesapp.interfaces.note.NoteClicked
import com.bethwelamkenya.notesapp.interfaces.note.PassData
import com.bethwelamkenya.notesapp.interfaces.note.PassNote
import com.bethwelamkenya.notesapp.models.Notes
import com.bethwelamkenya.notesapp.ui.NotesActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class HomeFragment : Fragment(), NoteClicked {

    private lateinit var addNote: ExtendedFloatingActionButton
    private val passData = PassData()
    private lateinit var adapter: NotesDatabaseAdapter
    private lateinit var recyclerAdapter: NotesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesActivity: Activity
    private lateinit var notes: ArrayList<Notes>
    private var passNote: PassNote? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        notesActivity = NotesActivity()
        adapter = NotesDatabaseAdapter(this.requireContext())
//        passNote = this.requireContext() as PassNote
        addNote = view.findViewById(R.id.addNote)
        notes = adapter.getNotes()!!
        recyclerView = view.findViewById(R.id.notesRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerAdapter = NotesAdapter(this.requireContext(), notes, this)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        recyclerView.adapter = recyclerAdapter
        addNote.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_addEditFragment) }
        return view
    }

    // This method insures that the Activity has actually implemented our
    // listener and that it isn't null.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PassNote) {
            passNote = context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement PassNote"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        passNote = null
    }

    override fun onNoteClicked(notes: Notes) {
//        passData.notes = notes
//        findNavController().navigate(R.id.action_homeFragment_to_addEditFragment)
        passNote?.passedNote(notes)
    }

    override fun onNoteLongClicked(notes: Notes, cardView: CardView) {
    }
}