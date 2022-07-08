package com.bunty.roomnotesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNote : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var btnEditDeleteClick: ImageView
    private lateinit var viewModel: NotesViewModel
    private var notesId = -1
    private lateinit var tvAppBarTitle: TextView
    private lateinit var backBtn: ImageView
    var notesType: String = ""

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        etTitle = findViewById(R.id.etNotesTitle)
        etDesc = findViewById(R.id.etNotesDesc)
        tvAppBarTitle = findViewById(R.id.tvAppBarTitle)
        backBtn = findViewById(R.id.back)

        btnEditDeleteClick = findViewById(R.id.btnUpdateEdit)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]

        notesType = intent.getStringExtra("noteType").toString()

        if (notesType == "Edit") {
            val notesTitle = intent.getStringExtra("noteTitle")
            val notesDesc = intent.getStringExtra("noteDesc")
            notesId = intent.getIntExtra("noteId", -1)
            etTitle.setText(notesTitle)
            etDesc.setText(notesDesc)
            //   btnEditDeleteClick.text = "---* Update Note *---"
            tvAppBarTitle.text = "Update Notes"
        } else {
            tvAppBarTitle.text = " Add New Notes "
            //  btnEditDeleteClick.text = "Save Note"
        }

        btnEditDeleteClick.setOnClickListener {
            saveNotes()
        }

        backBtn.setOnClickListener {
            saveNotes()
            onBackPressed()
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun saveNotes() {
        val notesTitle = etTitle.text.toString().trim()
        val notesDesc = etDesc.text.toString().trim()
        if (notesType.equals("Edit")) {
            if (notesTitle.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val updateNote = Notes(notesTitle, notesDesc, currentDate)
                updateNote.id = notesId
                viewModel.updateNote(updateNote)
                Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "please Add title and desc ", Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            if (notesTitle.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val newNote = Notes(notesTitle, notesDesc, currentDate)
                viewModel.addNote(newNote)
                Toast.makeText(this, "Notes added Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "please Add title and desc ", Toast.LENGTH_SHORT).show()
                return
            }
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        saveNotes()
        super.onBackPressed()
    }


}