package com.bethwelamkenya.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bethwelamkenya.notesapp.database.note.NotesDatabaseAdapter
import com.bethwelamkenya.notesapp.databinding.ActivityLauncherBinding
import com.bethwelamkenya.notesapp.databinding.ActivityMainBinding
import com.bethwelamkenya.notesapp.ui.MainActivity
import com.bethwelamkenya.notesapp.ui.NotesActivity

class LauncherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = NotesDatabaseAdapter(this)
        adapter.getNotes()
//        startActivity(Intent(this, MainActivity::class.java))
        startActivity(Intent(this, NotesActivity::class.java))
    }
}