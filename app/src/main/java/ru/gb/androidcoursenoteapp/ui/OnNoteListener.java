package ru.gb.androidcoursenoteapp.ui;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public interface OnNoteListener {
    void onClickNote(NoteEntity noteEntity);
    void onDeleteNote(NoteEntity noteEntity);
}
