package ru.gb.androidcoursenoteapp.ui;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public interface Controller {
    void showNoteFragment(NoteEntity noteEntity);
    void showNewNoteFragment();
    void showAppStats();
    void onAddNote();
    void onEditNote(NoteEntity noteEntity);
    void onDeleteNote(NoteEntity noteEntity);
}
