package com.pinkira.pinkyranotes.notesoverview;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pinkira.pinkyranotes.db.database.AppDatabase;
import com.pinkira.pinkyranotes.db.note.Note;
import com.pinkira.pinkyranotes.db.note.NoteDao;

import java.util.List;

/**
 * Presenter for 'NotesOverview' activity
 */
public class NotesOverviewPresenter implements NotesOverviewContract.UserActionsListener {

    private Context context;

    public NotesOverviewPresenter(Context ctx) {
        this.context = ctx;
    }

    @Override
    public List<Note> loadNotes() {
        return getNoteDao().loadAllNotes();
    }

    @Override
    public void addNewNote(@NonNull Note note) {
        getNoteDao().insertNote(note);
    }

    @Override
    public void openNote(@NonNull Long id) {
        getNoteDao().loadNoteFromId(id);
    }

    private NoteDao getNoteDao() {
        return AppDatabase.getDatabase(context).noteDao();
    }
}
