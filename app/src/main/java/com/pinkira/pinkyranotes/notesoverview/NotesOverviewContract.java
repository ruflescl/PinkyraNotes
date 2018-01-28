package com.pinkira.pinkyranotes.notesoverview;

/**
 * Contract interfaces for specifying interactions between  {@link NotesOverviewActivity} and
 * {@link NotesOverviewPresenter}
 */
public interface NotesOverviewContract {
    /**
     * 'User to Business' interaction contract
     */
    interface UserActions {
        void loadNotes();
        void addNewNote();
        void openNote();
    }

    /**
     * 'Business to User' interaction contract
     */
    interface PresenterActions {

    }
}
