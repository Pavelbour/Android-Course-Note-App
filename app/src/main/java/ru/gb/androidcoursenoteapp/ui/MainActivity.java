package ru.gb.androidcoursenoteapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.data.CacheNoteRepository;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;
import ru.gb.androidcoursenoteapp.ui.NoteAdapter;

public class MainActivity extends AppCompatActivity implements OnNoteListener{
    private static final int NOTE_REQUEST_CODE = 42;

    private NoteRepository noteRepository;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private Button addNewNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteRepository = App.get(this).getNoteRepository();

        addNewNoteButton = findViewById(R.id.add_new_note_button);
        addNewNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewNoteActivity.class);
            startActivityForResult(intent, NOTE_REQUEST_CODE);
        });

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.main_activity_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        adapter.setOnNoteListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickNote(NoteEntity noteEntity) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteActivity.NOTE_EXTRA_KEY, noteEntity);
        Toast.makeText(this, noteEntity.getTitle(), Toast.LENGTH_SHORT);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.setData(noteRepository.getNotes());
        }
    }
}