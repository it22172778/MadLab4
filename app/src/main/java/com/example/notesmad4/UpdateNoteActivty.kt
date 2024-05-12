package com.example.notesmad4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesmad4.databinding.ActivityAddNoteBinding

class UpdateNoteActivty : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
           finish()
           return
        }

        val note = db.getNoteByID(noteId)
        binding.titleEditText.setText(note.title) //want to change button id
        binding.contentEditText.setText(note.content)////want to change button id


        binding.saveButton.setOnClickListener {
            val newTitle = binding.titleEditText.text.toString()
            val newContent = binding.contentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()

            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()

        }



    }
}