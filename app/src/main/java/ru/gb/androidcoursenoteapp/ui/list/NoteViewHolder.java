package ru.gb.androidcoursenoteapp.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.androidcoursenoteapp.R;
import ru.gb.androidcoursenoteapp.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView = itemView.findViewById(R.id.item_note__note_title_edit_text);
    private final TextView contentTextView = itemView.findViewById(R.id.item_note__note_content_edit_text);
    private final TextView dateTextView = itemView.findViewById(R.id.item_note__note_date_edit_text);
    private final AppCompatImageView deleteImageView = itemView.findViewById(R.id.item_note__delete_image_view);

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
        deleteImageView.setOnClickListener(v -> onNoteListener.onDeleteNote(note));

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getNote());
        dateTextView.setText(note.getDate());
    }
}
