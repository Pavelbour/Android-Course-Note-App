package ru.gb.androidcoursenoteapp.ui.new_note;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;
import ru.gb.androidcoursenoteapp.ui.Controller;

public class NewNoteFragment extends Fragment {
    protected NoteRepository noteRepository;
    private Controller controller;

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
        return inflater.inflate(R.layout.activity_add_new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        noteRepository = App.get().getNoteRepository();

        Button button = view.findViewById(R.id.add_new_note__fragment_notes_list__add_new_note_button);

        EditText titleEditText = view.findViewById(R.id.add_new_note__note_title_edit_text);
        EditText contentEditText = view.findViewById(R.id.add_new_note__note_note_edit_text);
        EditText dateEditText = view.findViewById(R.id.add_new_note__note_date_edit_text);

        button.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            String date = dateEditText.getText().toString();

            NoteEntity note = new NoteEntity(noteRepository.getRepositorySize() + 1, title, content, date);
            noteRepository.addNote(note);
            controller.onAddNote();
        });
    }
}
