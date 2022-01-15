package ru.gb.androidcoursenoteapp;

import android.app.Application;
import android.content.Context;

import ru.gb.androidcoursenoteapp.data.CacheNoteRepository;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class App extends Application {
    private NoteRepository noteRepository = new CacheNoteRepository();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
