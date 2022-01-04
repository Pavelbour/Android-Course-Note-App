package ru.gb.androidcoursenoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.gb.androidcoursenoteapp.data.CacheNoteRepository;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;
import ru.gb.androidcoursenoteapp.ui.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    private NoteRepository noteRepository = new CacheNoteRepository();
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.main_activity_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());

        recyclerView.setAdapter(adapter);
    }
}