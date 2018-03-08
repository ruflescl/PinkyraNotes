package com.pinkyra.pinkyranotes.noteeditor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pinkyra.pinkyranotes.R;
import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;

/**
 * Note editor fragment for choosing the color of the note
 */
public class NoteEditorColorAccentPickerFragment extends Fragment {
    // Fragment name tag
    public static final String TAG = "NoteEditorColorAccentPickerFragment";

    // The fragment initialization parameters
    private static final String ARG_INIT_COLOR = "ARG_INIT_COLOR";

    // Initial color accent (view initialization)
    private Integer initialColor;

    private View colorAccentView;
    private Spinner colorAccentSpinner;

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
        if (getArguments() != null) {
            initialColor = getArguments().getInt(ARG_INIT_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.content_note_editor_color_accent_picker, container, false);

        colorAccentView = baseView.findViewById(R.id.cent_view_color_indicator);

        colorAccentSpinner = baseView.findViewById(R.id.cent_spin_color_indicator);

        colorAccentSpinnerAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,
                NoteColorAccent.Colors.getColorStrings(this.getContext()));

        colorAccentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorAccentSpinner.setAdapter(colorAccentSpinnerAdapter);
        colorAccentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                NoteColorAccent.Colors selectedColor = NoteColorAccent.Colors.getColorFromString(Note.this,
                        (String) adapterView.getSelectedItem());
                        */

                //changeColorAccent(selectedColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return baseView;
    }

    private void changeColorAccent(@NonNull NoteColorAccent.Colors selectedColor) {
        colorAccentView.setBackgroundResource(selectedColor.getColorResource());
    }
}
