package com.pinkyra.pinkyranotes.noteeditor.texteditor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.pinkyra.pinkyranotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

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

    enum EditStates {
        DISABLED,
        ENABLED
    }
    EditStates currentEditState = EditStates.DISABLED;

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

        disableTitleContentEdition();

        titleEditText.setText("Teste Teste Teste Teste Teste Teste Teste Teste");
        contentEditText.setText("Teste\nTeste\nTeste\nTeste\nTeste\nTeste\nTeste\nTeste");

        return baseView;
    }

    /**
     * Business rule: If the editing fields are allowing edition, disable and save the note.
     * Else, let the activity perform the back press.
     */
    public boolean doCheckBackPressedRule() {
        if (currentEditState == EditStates.ENABLED) {
            currentEditState = EditStates.DISABLED;
            disableTitleContentEdition();
            showSaveAndExitToast();
            return false;
        }
        return true;
    }

    private void showSaveAndExitToast() {
        Toast.makeText(this.getContext(),
                "Nota salva! Para sair, pressione o botão 'Voltar' novamente!",
                Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.cent_eddt_note_title)
    public void onTitleTextClick() {
        if (currentEditState == EditStates.DISABLED) showEditEnablingToast();
    }

    @OnClick(R.id.cent_eddt_note_content)
    public void onContentTextClick() {
        if (currentEditState == EditStates.DISABLED) showEditEnablingToast();
    }

    private void showEditEnablingToast() {
        Toast.makeText(this.getContext(),
                "Para habilitar a edição da nota, clique e segure em uma das áreas de edição!",
                Toast.LENGTH_LONG).show();
    }

    @OnLongClick(R.id.cent_eddt_note_title)
    public boolean onTitleTextLongClick() {
        enableTitleContentEdition(titleEditText);
        return true;
    }

    @OnLongClick(R.id.cent_eddt_note_content)
    public boolean onContentTextLongClick() {
        enableTitleContentEdition(contentEditText);
        return true;
    }

    private void disableTitleContentEdition() {
        titleEditText.setClickable(true);
        contentEditText.setClickable(true);
        titleEditText.setFocusable(false);
        contentEditText.setFocusable(false);
    }

    public void enableTitleContentEdition(final EditText viewToOpenKeyboard) {
        currentEditState = EditStates.ENABLED;

        titleEditText.post(new Runnable() {
            @Override
            public void run() {
                titleEditText.setClickable(false);
                titleEditText.setFocusableInTouchMode(true);
            }
        });

        contentEditText.post(new Runnable() {
            @Override
            public void run() {
                titleEditText.setClickable(false);
                contentEditText.setFocusableInTouchMode(true);
            }
        });

        viewToOpenKeyboard.post(new Runnable() {
            @Override
            public void run() {
                viewToOpenKeyboard.requestFocus();
                InputMethodManager imm = (InputMethodManager) NoteEditorTextFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });
    }
}
