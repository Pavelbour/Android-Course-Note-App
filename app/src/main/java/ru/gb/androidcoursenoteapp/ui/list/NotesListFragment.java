package ru.gb.androidcoursenoteapp.ui.list;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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

        final Toolbar toolbar = view.findViewById(R.id.fragment_notes_list__first_toolbar);
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.first_menu, menu);
        menu.findItem(R.id.first_menu__add_new_note).setOnMenuItemClickListener(this::onOptionsItemSelected);

        initRecycler(view);

        addNewNoteButton.setOnClickListener(v -> {
            controller.showNewNoteFragment();
        });

        ((AppCompatActivity) requireActivity()).setActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        controller.showNewNoteFragment();
        return true;
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
        new AlertDialog.Builder(getContext())
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    noteRepository.deleteNote(noteEntity);
                    adapter.deleteItem(noteEntity);

                    final Snackbar snackbar = Snackbar.make(getView(), "The note " + noteEntity.getTitle() + "deleted.", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setTextColor(Color.RED);
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAction("Close", snackView -> {
                        snackbar.dismiss();
                    });
                    snackbar.show();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    // pass
                })
                .show();
    }

    public void onEditNote(NoteEntity noteEntity) {
        adapter.editItem(noteEntity);
    }

    public void refreshList() {
        adapter.setData(noteRepository.getNotes());
    }
}
