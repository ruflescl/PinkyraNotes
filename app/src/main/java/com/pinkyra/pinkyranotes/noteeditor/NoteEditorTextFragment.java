package com.pinkyra.pinkyranotes.noteeditor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinkyra.pinkyranotes.R;

/**
 * Note editor fragment for editing text-based notes
 */
public class NoteEditorTextFragment extends Fragment {
    // Fragment name tag
    public static final String TAG = "NoteEditorTextFragment";

    // The fragment initialization parameters
    private static final String ARG_NOTE_ID = "ARG_NOTE_ID";

    private Long noteId;

    public NoteEditorTextFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param noteId Note ID to be edited. If null, a new note will be created.
     *
     * @return A new instance of fragment NoteEditorTextFragment.
     */
    public static NoteEditorTextFragment newInstance(@Nullable Long noteId) {
        NoteEditorTextFragment fragment = new NoteEditorTextFragment();
        Bundle args = new Bundle();

        if (noteId != null) args.putLong(ARG_NOTE_ID, noteId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(ARG_NOTE_ID)) noteId = getArguments().getLong(ARG_NOTE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.content_note_editor_text_note, container, false);

        return baseView;
    }
}
