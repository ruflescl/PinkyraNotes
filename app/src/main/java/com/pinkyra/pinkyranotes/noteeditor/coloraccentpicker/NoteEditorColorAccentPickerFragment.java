package com.pinkyra.pinkyranotes.noteeditor.coloraccentpicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pinkyra.pinkyranotes.Injection;
import com.pinkyra.pinkyranotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * Note editor fragment for choosing the color of the note
 */
public class NoteEditorColorAccentPickerFragment extends Fragment
        implements NoteEditorColorAccentPickerContract.View {
    // Fragment name tag
    public static final String TAG = "NoteEditorColorAccentPickerFragment";

    // The fragment initialization parameters
    private static final String ARG_INIT_COLOR = "ARG_INIT_COLOR";

    private NoteEditorColorAccentPickerContract.UserActionsListener userActionsListener;

    @BindView(R.id.cent_view_color_indicator) View colorAccentView;
    @BindView(R.id.cent_spin_color_indicator) Spinner colorAccentSpinner;
    private ArrayAdapter<String> colorAccentSpinnerAdapter;

    public NoteEditorColorAccentPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param initialColorId Color ID to be displayed.
     * @return A new instance of fragment NoteEditorColorAccentPickerFragment.
     */
    public static NoteEditorColorAccentPickerFragment newInstance(@NonNull Integer initialColorId) {
        NoteEditorColorAccentPickerFragment fragment = new NoteEditorColorAccentPickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INIT_COLOR, initialColorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userActionsListener = new NoteEditorColorAccentPickerPresenter(
                Injection.provideNoteRepository(this.getContext()),
                this,
                this.getContext());

        if (getArguments() != null) {
            userActionsListener.setupInitialColor(getArguments().getInt(ARG_INIT_COLOR));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.content_note_editor_color_accent_picker, container, false);

        ButterKnife.bind(this, baseView);

        initColorSpinner();

        userActionsListener.onViewsCreated();

        return baseView;
    }

    private void initColorSpinner() {
        colorAccentSpinner.setAdapter(userActionsListener.getColorsAdapter());
    }

    @OnItemSelected(R.id.cent_spin_color_indicator)
    void onSpinColorItemSelected(int position) {
        userActionsListener.onColorSpinnerItemSelected(position);
    }

    @Override
    public void changeColorAccent(int colorId) {
        colorAccentView.setBackgroundResource(colorId);
    }

    @Override
    public void changeSpinnerPosition(final int position) {
        colorAccentSpinner.post(new Runnable() {
            @Override
            public void run() {
                colorAccentSpinner.setSelection(position);
            }
        });
    }
}
