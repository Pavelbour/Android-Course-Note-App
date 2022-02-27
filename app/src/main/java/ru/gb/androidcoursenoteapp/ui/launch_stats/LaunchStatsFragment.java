package ru.gb.androidcoursenoteapp.ui.launch_stats;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.androidcoursenoteapp.App;
import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.ui.Controller;

public class LaunchStatsFragment extends Fragment {
    private Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NotesListFragment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lunch_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TextView launchStats = view.findViewById(R.id.fragment_lanch_stats__lunch_stats);
        launchStats.setText("The application has been launched " + App.get().getLunchNumber() + " times.");
    }
}
