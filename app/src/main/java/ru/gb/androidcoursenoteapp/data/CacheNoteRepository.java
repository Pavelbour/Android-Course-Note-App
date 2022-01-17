package ru.gb.androidcoursenoteapp.data;

import java.util.ArrayList;
import java.util.List;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class CacheNoteRepository implements NoteRepository {
    private final ArrayList<NoteEntity> cache = new ArrayList<>();

    public CacheNoteRepository() {
        cache.addAll(createDummyNotesData());
    }

    private static ArrayList<NoteEntity> createDummyNotesData() {
        final ArrayList<NoteEntity> noteEntities = new ArrayList<>();
        noteEntities.add(new NoteEntity(1,"Note 1", "Note 1 content", "1st January 2022"));
        noteEntities.add(new NoteEntity(2,"Note 2", "Note 2 content", "2nd January 2022"));
        noteEntities.add(new NoteEntity(3,"Note 3", "Note 3 content", "3rd January 2022"));
        return noteEntities;
    }

    @Override
    public List<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Override
    public void addNote(NoteEntity note) {
        cache.add(note);
    }

    @Override
    public void editNote(NoteEntity note) {
        cache.set(findPosition(note), note);
    }

    @Override
    public int getRepositorySize() {
        return cache.size();
    }

    @Override
    public void deleteNote(NoteEntity note) {
        try {
            cache.remove(findPosition(note));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    private int findPosition(NoteEntity note) {
        for (int i = 0; i < cache.size(); i++) {
            if (note.getId() == cache.get(i).getId()) {
                return i;
            }
        }
        throw new IllegalArgumentException("The note not found.");
    }
}
