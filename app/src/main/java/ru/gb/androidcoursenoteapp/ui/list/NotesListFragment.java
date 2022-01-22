package ru.gb.androidcoursenoteapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;
import ru.gb.androidcoursenoteapp.ui.Controller;

public class NotesListFragment extends Fragment implements OnNoteListener {
    private static final String ADAPTER_ARG_KEY = "ADAPTER_ARG_KEY";

    private Controller controller;
    private NoteRepository noteRepository;
    private RecyclerView recyclerView;
    private Button addNewNoteButton;
    private NoteAdapter adapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NotesListFragment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        noteRepository = App.get().getNoteRepository();
        addNewNoteButton = view.findViewById(R.id.fragment_notes_list__add_new_note_button);

        initRecycler(view);

        addNewNoteButton.setOnClickListener(v -> {
            controller.showNewNoteFragment();
        });
    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.fragment_notes_list__recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        adapter.setOnNoteListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickNote(NoteEntity noteEntity) {
        controller.showNoteFragment(noteEntity);
    }

    @Override
    public void onDeleteNote(NoteEntity noteEntity) {
        noteRepository.deleteNote(noteEntity);
        adapter.deleteItem(noteEntity);
    }

    public void onEditNote(NoteEntity noteEntity) {
        adapter.editItem(noteEntity);
    }

    public void refreshList () {
        adapter.setData(noteRepository.getNotes());
    }
}
