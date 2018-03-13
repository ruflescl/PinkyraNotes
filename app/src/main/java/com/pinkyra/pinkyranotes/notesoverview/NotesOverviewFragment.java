package com.pinkyra.pinkyranotes.notesoverview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinkyra.pinkyranotes.Injection;
import com.pinkyra.pinkyranotes.R;
import com.pinkyra.pinkyranotes.db.note.Note;
import com.pinkyra.pinkyranotes.noteeditor.NoteEditorActivity;
import com.pinkyra.pinkyranotes.notesoverview.notedetail.api.NoteItemListener;
import com.pinkyra.pinkyranotes.notesoverview.notedetail.notecarddetail.NoteCardDetailAdapter;
import com.pinkyra.pinkyranotes.notesoverview.notedetail.notecarddetail.NoteCardDetailBuilder;
import com.pinkyra.pinkyranotes.util.SwipeRefreshLayoutBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment
 */
public class NotesOverviewFragment
        extends Fragment
        implements NotesOverviewContract.View, NoteItemListener {

    private NotesOverviewContract.UserActionsListener userActionsListener;
    private NoteCardDetailAdapter cardDetailAdapter;

    @BindView(R.id.cnom_cont_notes_detail_list) RecyclerView notesRecyclerViewList;
    @BindView(R.id.cnom_cont_empty_content) View emptyListView;
    @BindView(R.id.cnom_swrl_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

    public NotesOverviewFragment() {
    }

    public static NotesOverviewFragment newInstance() {
        return new NotesOverviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userActionsListener = new NotesOverviewPresenter(
                Injection.provideSharedPreferencesHelper(this.getContext()),
                Injection.provideNoteRepository(this.getContext()),
                this, this);

        cardDetailAdapter = new NoteCardDetailAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        userActionsListener.loadNotes();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.content_notes_overview_master, container, false);

        ButterKnife.bind(this, baseView);

        // Setting up notes recycler view
        notesRecyclerViewList.setAdapter(cardDetailAdapter);
        NoteCardDetailBuilder.setupLayoutManager(this.getContext(),
                notesRecyclerViewList,
                userActionsListener.getCardDetailPrefStyle(),
                userActionsListener.getCardDetailPrefColumnCount());

        // Setting up swipe refresh (loadNotes user case related)
        SwipeRefreshLayoutBuilder.setup(getActivity(), swipeRefreshLayout, new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userActionsListener.loadNotes();
            }
        });

        return baseView;
    }

    public void onFabAddNote_Click() {
        userActionsListener.addNote();
    }

    @Override
    public void openAddNoteActivity() {
        Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
        startActivity(intent);
    }

    @Override
    public void setProgressIndicator(@NonNull final Boolean inProgress) {
        // Safety check for non-instantiated fragment content
        if (getView() == null) return;

        // Safety check for busy or concurrent access
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(inProgress);
            }
        });
    }

    @Override
    public void toggleEmptyAndListView(@NonNull Boolean hasData) {
        emptyListView.setVisibility(!hasData ? View.VISIBLE : View.GONE);
        notesRecyclerViewList.setVisibility(hasData ? View.VISIBLE : View.GONE);
    }

    @Override
    public void refreshNotesOverviewList() {

    }

    @Override
    public void showContextMenu(@NonNull String[] contextMenuContent) {
        // TODO: Implement logic and context menu view
    }

    @Override
    public void showNotes(List<Note> notesList) {
        cardDetailAdapter.replaceData(notesList);
    }

    @Override
    public void showNoteDetailActivity(Note note) {
        // TODO: Open
    }

    @Override
    public void onNoteClick(@NonNull Note note) {
        userActionsListener.viewNoteDetail(note);
    }

    @Override
    public void onNoteLongClick(@NonNull Note note) {
        userActionsListener.viewNoteContextMenu(note);
    }
}
