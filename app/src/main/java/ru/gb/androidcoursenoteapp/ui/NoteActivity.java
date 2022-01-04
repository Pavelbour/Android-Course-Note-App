package ru.gb.androidcoursenoteapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY";
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleTextView = findViewById(R.id.note_title_text_view);
        contentTextView = findViewById(R.id.note_content_text_view);
        dateTextView = findViewById(R.id.note_date_text_view);

        NoteEntity noteEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        titleTextView.setText(noteEntity.getTitle());
        contentTextView.setText(noteEntity.getNote());
        dateTextView.setText(noteEntity.getDate());
    }
}
