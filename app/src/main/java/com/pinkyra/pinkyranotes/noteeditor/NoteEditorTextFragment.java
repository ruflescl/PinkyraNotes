package com.pinkyra.pinkyranotes.noteeditor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pinkyra.pinkyranotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Note editor fragment for editing text-based notes
 */
public class NoteEditorTextFragment extends Fragment {
    // Fragment name tag
    public static final String TAG = "NoteEditorTextFragment";

    // The fragment initialization parameters
    private static final String ARG_NOTE_ID = "ARG_NOTE_ID";

    @BindView(R.id.cent_eddt_note_title) EditText titleEditText;
    @BindView(R.id.cent_eddt_note_content) EditText contentEditText;

    private Long noteId;

    int originalInputType_Title;
    int originalInputType_Content;

    enum EditFlags {
        DISABLED,
        FIRST_CLICK_DELAY,
        ENABLED
    }
    EditFlags currentEditFlag = EditFlags.DISABLED;

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

        ButterKnife.bind(this, baseView);

        initTitleContentViews();

        return baseView;
    }

    @OnClick(R.id.cent_eddt_note_content)
    public void onContentTextClick() {
        enableTitleContentEdition();
    }

    @OnClick(R.id.cent_eddt_note_title)
    public void onTitleTextClick() {
        enableTitleContentEdition();
    }

    private void initTitleContentViews() {
        originalInputType_Title = titleEditText.getInputType();
        originalInputType_Content = contentEditText.getInputType();

        titleEditText.setClickable(true);
        titleEditText.setInputType(InputType.TYPE_NULL);
        contentEditText.setClickable(true);
        contentEditText.setInputType(InputType.TYPE_NULL);
    }

    // TODO: Create a delayed timer to enable edition on doubleclick

    public void enableTitleContentEdition() {
        titleEditText.post(new Runnable() {
            @Override
            public void run() {
                titleEditText.setClickable(false);
                titleEditText.setInputType(originalInputType_Title);
                //titleEditText.postInvalidate();
            }
        });

        contentEditText.post(new Runnable() {
            @Override
            public void run() {
                contentEditText.setClickable(false);
                contentEditText.setInputType(originalInputType_Content);
                //titleEditText.postInvalidate();
            }
        });
    }
}
