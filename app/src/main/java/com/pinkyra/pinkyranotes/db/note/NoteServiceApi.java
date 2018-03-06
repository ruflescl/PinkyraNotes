package com.pinkyra.pinkyranotes.db.note;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Note api exposing base access methods
 */
public interface NoteServiceApi {

    LiveData<List<Note>> getAllNotes();

    Note getNoteById(@NonNull Long id);

    void saveNote(@NonNull Note note);
}
