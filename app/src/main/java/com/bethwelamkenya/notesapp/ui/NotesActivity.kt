package com.bethwelamkenya.notesapp.ui

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.bethwelamkenya.notesapp.R
import com.bethwelamkenya.notesapp.databinding.ActivityNotesBinding
import com.bethwelamkenya.notesapp.fragments.note.AddEditFragment
import com.bethwelamkenya.notesapp.interfaces.note.PassNote
import com.bethwelamkenya.notesapp.interfaces.note.PassNoteFromActivity
import com.bethwelamkenya.notesapp.models.Notes
import java.nio.channels.AsynchronousFileChannel.open

class NotesActivity : AppCompatActivity() , PassNote {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var navController: NavController
    private lateinit var navigationHostFragment: NavHostFragment
    private var passNoteFromActivity: PassNoteFromActivity? = null
    private lateinit var addEditFragment: AddEditFragment
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.mainContainer, R.string.open, R.string.close)
        binding.mainContainer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { binding.mainContainer.openDrawer(binding.notesNavView) }
        navigationHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navigationHostFragment.navController
//        setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun passedNote(note: Notes) {
//        val addEditFragment: AddEditFragment = supportFragmentManager.get()
//        val addEditFragment: AddEditFragment = navigationHostFragment.parentFragmentManager.findFragmentById(R.id.addEditFragment) as AddEditFragment
//        val addEditFragment: AddEditFragment = supportFragmentManager.findFragmentById(R.id.addEditFragment) as AddEditFragment
//        addEditFragment.passeNote(note)
        findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_addEditFragment)
        println("true")
        println("true")
        println("true")
        println("true")
        println("true")
        println("true")
        println("true")
        addEditFragment = AddEditFragment()
        passNoteFromActivity = addEditFragment
        passNoteFromActivity?.passNote(note)
//        addEditFragment.passeNote(note)
    }
}