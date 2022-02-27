package ru.gb.androidcoursenoteapp.ui.note;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;
import ru.gb.androidcoursenoteapp.ui.Controller;

public class NoteFragment extends Fragment {
    private static final String NOTE_ARG_KEY = "NOTE_ARG_KEY";

    protected NoteRepository noteRepository;
    private Controller controller;
    private EditText titleTextView;
    private EditText contentTextView;
    private EditText dateTextView;
    private Button saveChangesButton;
    private NoteEntity noteEntity;

    public static NoteFragment newInstance (NoteEntity noteEntity) {
        NoteFragment fragment = new NoteFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(NOTE_ARG_KEY, noteEntity);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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
        return inflater.inflate(R.layout.activity_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        noteEntity = getArguments().getParcelable(NOTE_ARG_KEY);
        noteRepository = App.get().getNoteRepository();

        final Toolbar toolbar = view.findViewById(R.id.item_note__second_toolbar);
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.second_menu, menu);
        menu.findItem(R.id.second_menu__delete_note).setOnMenuItemClickListener(this::onOptionsItemSelected);
        menu.findItem(R.id.second_menu__app_stats).setOnMenuItemClickListener(this::onOptionsItemSelected);

        titleTextView = view.findViewById(R.id.item_note__note_title_edit_text);
        contentTextView = view.findViewById(R.id.item_note__note_content_edit_text);
        dateTextView = view.findViewById(R.id.item_note__note_date_edit_text);
        saveChangesButton = view.findViewById(R.id.activity_note__note_save_changes_button);

        titleTextView.setText(noteEntity.getTitle());
        contentTextView.setText(noteEntity.getNote());
        dateTextView.setText(noteEntity.getDate());

        saveChangesButton.setOnClickListener(v-> {
            noteEntity.setTitle(titleTextView.getText().toString());
            noteEntity.setNote(contentTextView.getText().toString());
            noteEntity.setDate(dateTextView.getText().toString());

            noteRepository.editNote(noteEntity);

            controller.onEditNote(noteEntity);
        });

        ((AppCompatActivity) requireActivity()).setActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.second_menu__delete_note:
                controller.onDeleteNote(noteEntity);
                break;
            case R.id.second_menu__app_stats:
                controller.showAppStats();
                break;
        }
        return true;
    }
}
