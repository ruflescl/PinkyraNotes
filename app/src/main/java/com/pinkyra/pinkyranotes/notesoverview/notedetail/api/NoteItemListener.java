package com.pinkyra.pinkyranotes.notesoverview.notedetail.api;

import android.support.annotation.NonNull;

import com.pinkyra.pinkyranotes.db.note.Note;

/**
 * Interface that defines user interactions with note details in {@link com.pinkyra.pinkyranotes.notesoverview.NotesOverviewFragment}
 */
public interface NoteItemListener {
    void onNoteClick(@NonNull Note note);

    void onNoteLongClick(@NonNull Note note);
}
