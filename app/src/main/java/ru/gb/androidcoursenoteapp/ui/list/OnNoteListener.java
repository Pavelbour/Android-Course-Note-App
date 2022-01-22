package ru.gb.androidcoursenoteapp.ui.list;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public interface OnNoteListener {
    void onClickNote(NoteEntity noteEntity);
    void onDeleteNote(NoteEntity noteEntity);
}
