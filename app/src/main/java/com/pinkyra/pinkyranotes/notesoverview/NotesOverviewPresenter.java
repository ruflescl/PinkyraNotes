package com.pinkyra.pinkyranotes.notesoverview;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pinkyra.pinkyranotes.db.note.Note;
import com.pinkyra.pinkyranotes.db.note.NoteRepository;
import com.pinkyra.pinkyranotes.util.SharedPreferencesHelper;

import java.util.List;

/**
 * Presenter for '{@link NotesOverviewActivity}'
 */
public class NotesOverviewPresenter implements NotesOverviewContract.UserActionsListener {

    private NoteRepository noteRepo;
    private NotesOverviewContract.View notesOverview;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private LiveData<List<Note>> cachedNotes;
    private LifecycleOwner lifecycleOwner;

    public NotesOverviewPresenter(@NonNull SharedPreferencesHelper sharedPreferencesHelper,
                                  @NonNull NoteRepository noteRepo,
                                  @NonNull NotesOverviewContract.View notesOverview,
                                  @NonNull LifecycleOwner lifecycleOwner) {
        this.noteRepo = noteRepo;
        this.notesOverview = notesOverview;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void loadNotes() {
        notesOverview.setProgressIndicator(true); // TODO: Observe the live data to hide the progress indicator (?)

        cachedNotes = null;
        cachedNotes = noteRepo.getAllNotes();
        cachedNotes.observe(lifecycleOwner, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                notesOverview.showNotes(notes);
                notesOverview.toggleEmptyAndListView(notes != null && !notes.isEmpty());
            }
        });

        notesOverview.setProgressIndicator(false);
    }

    @Override
    public void addNote() {
        notesOverview.openAddNoteActivity();
    }

    @Override
    public void viewNoteDetail(@NonNull Note note) {
        notesOverview.showNoteDetailActivity(note);
    }

    @Override
    public void viewNoteContextMenu(@NonNull Note note) {
        String[] contextMenuContents = {};

        notesOverview.showContextMenu(contextMenuContents);
    }

    @Override
    public SharedPreferencesHelper.SharedPreferencesValues getCardDetailPrefStyle() {
        return sharedPreferencesHelper.getValue(SharedPreferencesHelper.SharedPreferencesKeys.NOTES_OVERVIEW__NOTE_DETAIL_STYLE);
    }

    @Override
    public SharedPreferencesHelper.SharedPreferencesValues getCardDetailPrefColumnCount() {
        return sharedPreferencesHelper.getValue(SharedPreferencesHelper.SharedPreferencesKeys.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT);
    }
}
