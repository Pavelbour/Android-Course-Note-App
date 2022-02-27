package ru.gb.androidcoursenoteapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesLaunchStats {
    private static final String SHARED_PREFS_NAME = "LaunchStats";
    private static final String SHARED_PREFS_LAUNCH_NUMBER_KEY = "LaunchNumber";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesLaunchStats(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public int getLaunchNumber() {
        return sharedPreferences.getInt(SHARED_PREFS_LAUNCH_NUMBER_KEY, 0);
    }

    public void incrementLaunchNumber() {
        sharedPreferences
                .edit()
                .putInt(SHARED_PREFS_LAUNCH_NUMBER_KEY, sharedPreferences.getInt(SHARED_PREFS_LAUNCH_NUMBER_KEY, 0) + 1)
                .apply();
    }
}
