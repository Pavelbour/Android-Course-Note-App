package ru.gb.androidcoursenoteapp.data;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.List;

import ru.gb.androidcoursenoteapp.domain.NoteEntity;
import ru.gb.androidcoursenoteapp.domain.NoteRepository;

public class SnappyDBNoteRepository implements NoteRepository {
    final public static String DB_KEY_PREFIX = "id_";
    private Context context;

    public SnappyDBNoteRepository(Context context) {
        this.context = context;
    }

    @Override
    public List<NoteEntity> getNotes() {
        final ArrayList<NoteEntity> noteList = new ArrayList<>();

        try {
            DB db = DBFactory.open(context);
            for (String[] batch : db.allKeysIterator().byBatch(10)) {
                for (String key : batch) {
                    NoteEntity note = db.getObject(key, NoteEntity.class);
                    noteList.add(note);
                }
            }

            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    @Override
    public void deleteNote(NoteEntity note) {
        try {
            DB db = DBFactory.open(context);
            db.del(DB_KEY_PREFIX + String.valueOf(note.getId()));

            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNote(NoteEntity note) {
        try {
            DB db = DBFactory.open(context);
            db.put(DB_KEY_PREFIX + String.valueOf(note.getId()), note);
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editNote(NoteEntity note) {
        try {
            DB db = DBFactory.open(context);
            db.put(DB_KEY_PREFIX + String.valueOf(note.getId()), note);

            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRepositorySize() {
        int size = 0;
        try {
            DB db = DBFactory.open(context);
            size = db.countKeys(DB_KEY_PREFIX);

            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        return size;
    }
}
