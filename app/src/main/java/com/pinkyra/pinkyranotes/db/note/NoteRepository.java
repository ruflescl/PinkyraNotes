package com.pinkyra.pinkyranotes.db.note;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Note repository that enables multiple channels for retrieving notes (repository design pattern)
 */
public class NoteRepository implements NoteServiceApi {

    private NoteDao noteDao;

    public NoteRepository(NoteDao localRepo) {
        noteDao = localRepo;
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return noteDao.loadAllNotes();
    }

    @Override
    public Note getNoteById(@NonNull Long id) {
        return noteDao.loadNoteFromId(id);
    }

    @Override
    public void saveNote(@NonNull Note note) {
        noteDao.insertNote(note);
    }
}
