package ru.gb.androidcoursenoteapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<NoteEntity> data = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public void setData(List<NoteEntity> noteList) {
        data = noteList;
    }

    public void setOnNoteListener(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NoteViewHolder(inflater, parent, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private NoteEntity getItem(int position) {
        return data.get(position);
    }

    public void deleteItem(NoteEntity noteEntity) {
        int index = data.indexOf(noteEntity);
        data.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
