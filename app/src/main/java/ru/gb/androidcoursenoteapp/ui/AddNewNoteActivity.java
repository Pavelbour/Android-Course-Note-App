package ru.gb.androidcoursenoteapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class AddNewNoteActivity extends AppCompatActivity {
    protected NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_note);

        noteRepository = App.get(this).getNoteRepository();

        Button button = findViewById(R.id.add_new_note_button);

        EditText titleEditText = findViewById(R.id.note_title_edit_text);
        EditText contentEditText = findViewById(R.id.note_note_edit_text);
        EditText dateEditText = findViewById(R.id.note_date_edit_text);

        button.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            String date = dateEditText.getText().toString();

            NoteEntity note = new NoteEntity(noteRepository.getRepositorySize() + 1, title, content, date);
            noteRepository.addNote(note);
            setResult(RESULT_OK);
            finish();
        });
    }
}
