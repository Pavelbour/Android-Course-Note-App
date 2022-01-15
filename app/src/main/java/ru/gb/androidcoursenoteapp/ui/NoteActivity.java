package ru.gb.androidcoursenoteapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class NoteActivity extends AppCompatActivity {
    protected NoteRepository noteRepository;
    public static final String NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY";
    private EditText titleTextView;
    private EditText contentTextView;
    private EditText dateTextView;
    private Button saveChangesButton;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRepository = App.get(this).getNoteRepository();
        setContentView(R.layout.activity_note);

        titleTextView = findViewById(R.id.activity_note__note_title_edit_text);
        contentTextView = findViewById(R.id.activity_note__note_content_edit_text);
        dateTextView = findViewById(R.id.activity_note__note_date_edit_text);
        saveChangesButton = findViewById(R.id.activity_note__note_save_changes_button);

        NoteEntity noteEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        titleTextView.setText(noteEntity.getTitle());
        contentTextView.setText(noteEntity.getNote());
        dateTextView.setText(noteEntity.getDate());

        saveChangesButton.setOnClickListener(v-> {
            noteEntity.setTitle(titleTextView.getText().toString());
            noteEntity.setNote(contentTextView.getText().toString());
            noteEntity.setDate(dateTextView.getText().toString());

            noteRepository.editNote(noteEntity);

            setResult(RESULT_OK);
            finish();
        });
    }
}
