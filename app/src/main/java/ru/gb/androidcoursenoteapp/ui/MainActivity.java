package ru.gb.androidcoursenoteapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.ui.launch_stats.LaunchStatsFragment;
import ru.gb.androidcoursenoteapp.ui.list.NotesListFragment;
import ru.gb.androidcoursenoteapp.ui.new_note.NewNoteFragment;
import ru.gb.androidcoursenoteapp.ui.note.NoteFragment;

public class MainActivity extends AppCompatActivity implements Controller {
    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment notesListFragment = new NotesListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__main_fragment_container, notesListFragment, TAG_LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onAddNote() {
        getSupportFragmentManager().popBackStack();
        NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        fragment.refreshList();

        final Snackbar snackbar = Snackbar.make(fragment.getView(), "A new note added successfully", Snackbar.LENGTH_INDEFINITE);
        snackbar.setTextColor(Color.GREEN);
        snackbar.setActionTextColor(Color.RED);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
        snackbar.setAction("Close", snackView -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

    @Override
    public void onEditNote(NoteEntity noteEntity) {
        getSupportFragmentManager().popBackStack();
        NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        fragment.onEditNote(noteEntity);

        Toast.makeText(this, "The changes saved.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteNote(NoteEntity noteEntity) {
        getSupportFragmentManager().popBackStack();
        NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        fragment.onDeleteNote(noteEntity);
    }

    @Override
    public void showNewNoteFragment() {
        Fragment newNoteFragment = new NewNoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__second_fragment_container, newNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showNoteFragment(NoteEntity noteEntity) {
        Fragment noteFragment = NoteFragment.newInstance(noteEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__second_fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showAppStats() {
        Fragment launchStatsFragment = new LaunchStatsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__second_fragment_container, launchStatsFragment)
                .addToBackStack(null)
                .commit();
    }
}