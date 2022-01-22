package ru.gb.androidcoursenoteapp;

import android.app.Application;
import android.content.Context;

import ru.gb.androidcoursenoteapp.data.CacheNoteRepository;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class App extends Application {
    private static App sInstance = null;
    private final NoteRepository noteRepository = new CacheNoteRepository();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
