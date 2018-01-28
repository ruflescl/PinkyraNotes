package com.pinkira.pinkyranotes.notesoverview;

import android.support.annotation.NonNull;

import com.pinkira.pinkyranotes.db.note.Note;

import java.util.List;

/**
 * Contract interfaces for specifying interactions between  {@link NotesOverviewActivity} and
 * {@link NotesOverviewPresenter}
 */
public interface NotesOverviewContract {
    /**
     * 'User to Business' interaction contract
     */
    interface UserActionsListener {
        List<Note> loadNotes();
        void addNewNote(@NonNull Note note);
        void openNote(@NonNull Long id);
    }

    /**
     * 'Business to User' interaction contract
     */
    interface PresenterCallback {
        void setProgressIndicator(@NonNull Boolean inProgress);
    }
}
