package com.bunty.roomnotesapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesClick, NotesDeleteClick {

    private lateinit var notesRv: RecyclerView
    private lateinit var floatingBtn: LinearLayout
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel
    private lateinit var tvNoDta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRv = findViewById(R.id.rvNotes)
        floatingBtn = findViewById(R.id.addButton)
        tvNoDta = findViewById(R.id.tvNoData)
        notesRv.layoutManager = GridLayoutManager(this, 2)
        notesAdapter = NotesAdapter(this, this, this)
        notesRv.adapter = notesAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]

        viewModel.allNotes.observe(this) { list ->
            list.let {
                notesAdapter.updateList(list, tvNoDta)
            }

        }

        floatingBtn.setOnClickListener {
            val intent = Intent(this, AddEditNote::class.java)
            startActivity(intent)
        }

    }

    override fun onNotesClick(notes: Notes) {
        val intent = Intent(this, AddEditNote::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notes.noteTitle)
        intent.putExtra("noteDesc", notes.noteDesc)
        intent.putExtra("noteId", notes.id)
        startActivity(intent)
    }

    override fun onNotesDeleteClick(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this, "${notes.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Really Exit?")
            .setMessage("Are you sure you want to exit?")
            .setNegativeButton("No", null)
            .setPositiveButton("Yes") { _, _ -> finishAffinity() }.create().show()
    }


}