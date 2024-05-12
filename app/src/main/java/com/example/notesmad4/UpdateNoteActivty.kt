package com.example.notesmad4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesmad4.databinding.ActivityAddNoteBinding
import com.example.notesmad4.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivty : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
           finish()
           return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title) //want to change button id
        binding.updateContentEditText.setText(note.content)////want to change button id


        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()

            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()

        }



    }
}