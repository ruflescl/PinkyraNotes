package com.pinkyra.pinkyranotes.notesoverview;

import android.support.annotation.NonNull;

import com.pinkyra.pinkyranotes.db.note.Note;
import com.pinkyra.pinkyranotes.util.SharedPreferencesHelper;

import java.util.List;

/**
 * Contract interfaces for specifying interactions between  {@link NotesOverviewActivity} and
 * {@link NotesOverviewPresenter}
 */
public interface NotesOverviewContract {
    /**
     * 'User to Business' interaction contract
     * <p>
     * Remember: Use 'use case' naming style when possible
     */
    interface UserActionsListener {
        void loadNotes();

        void addNote();

        void viewNoteDetail(@NonNull Note note);

        void viewNoteContextMenu(@NonNull Note note);

        SharedPreferencesHelper.SharedPreferencesValues getCardDetailPrefStyle();

        SharedPreferencesHelper.SharedPreferencesValues getCardDetailPrefColumnCount();
    }

    /**
     * 'Business to User' interaction contract
     */
    interface View {
        void openAddNoteActivity();

        void showNoteDetailActivity(Note note);

        void setProgressIndicator(@NonNull Boolean inProgress);

        void refreshNotesOverviewList();

        void showContextMenu(String[] contextMenuContent);

        void showNotes(List<Note> notesList);

        void toggleEmptyAndListView(@NonNull Boolean hasData);
    }
}
