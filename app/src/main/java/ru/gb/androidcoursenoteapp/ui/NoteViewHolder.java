package ru.gb.androidcoursenoteapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView = itemView.findViewById(R.id.note_title_text_view);
    private final TextView contentTextView = itemView.findViewById(R.id.note_content_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.note_date_text_view);

    private OnNoteListener onNoteListener;

    public NoteViewHolder (
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent,
            OnNoteListener onNoteListener
            ) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        this.onNoteListener = onNoteListener;
    }

    public void bind(NoteEntity note) {
        itemView.setOnClickListener(v -> onNoteListener.onClickNote(note));

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getNote());
        dateTextView.setText(note.getDate());
    }
}
