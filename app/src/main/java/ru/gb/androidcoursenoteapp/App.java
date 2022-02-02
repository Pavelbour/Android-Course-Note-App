package ru.gb.androidcoursenoteapp;

import android.app.Application;

import ru.gb.androidcoursenoteapp.data.CacheNoteRepository;
import ru.gb.androidcoursenoteapp.data.SharedPreferencesLaunchStats;
import ru.gb.androidcoursenoteapp.data.SnappyDBNoteRepository;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class App extends Application {
    private static App sInstance = null;
//    private final NoteRepository noteRepository = new CacheNoteRepository();
    private final NoteRepository noteRepository = new SnappyDBNoteRepository(this);
    public SharedPreferencesLaunchStats sharedPreferencesLaunchStats;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sharedPreferencesLaunchStats = new SharedPreferencesLaunchStats(this);
        sharedPreferencesLaunchStats.incrementLaunchNumber();
    }

    public static App get() {
        return sInstance;
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }

    public int getLunchNumber() {
        return sharedPreferencesLaunchStats.getLaunchNumber();
    }
}
