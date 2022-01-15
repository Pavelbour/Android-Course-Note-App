package ru.gb.androidcoursenoteapp.domain;

import java.util.List;

public interface NoteRepository {
    List<NoteEntity> getNotes();

    void deleteNote(NoteEntity note);
    void addNote(NoteEntity note);
    int getRepositorySize();
}
