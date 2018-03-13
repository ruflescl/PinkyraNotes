package com.pinkyra.pinkyranotes.noteeditor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;
import com.pinkyra.pinkyranotes.db.note.NoteRepository;

/**
 * Presenter for '{@link NoteEditorColorAccentPickerFragment}'
 */
public class NoteEditorColorAccentPickerPresenter
        implements NoteEditorColorAccentPickerContract.UserActionsListener {

    private NoteRepository noteRepo;
    private NoteEditorColorAccentPickerContract.View noteEditor;
    private Context context;
    private ArrayAdapter<String> colorAccentSpinnerAdapter;

    // Initial color accent (view initialization)
    private NoteColorAccent.Colors currentColor = null;

    public NoteEditorColorAccentPickerPresenter(@NonNull NoteRepository noteRepo,
                                                @NonNull NoteEditorColorAccentPickerContract.View noteEditor,
                                                @NonNull Context context) {
        this.noteRepo = noteRepo;
        this.noteEditor = noteEditor;
        this.context = context;
    }

    @Override
    public void onViewsCreated() {
        if (hasSelectedColor()) {
            noteEditor.changeColorAccent(currentColor.getColorResource());
            noteEditor.changeSpinnerPosition(getSpinnerPositionFromCurrentColor());
        }
    }

    private int getSpinnerPositionFromCurrentColor() {
        NoteColorAccent.Colors[] colorsArray = NoteColorAccent.Colors.values();
        for (int i = 0; i < colorsArray.length; i++) {
            if (colorsArray[i].equals(currentColor)) return i;
        }
        return 0;
    }

    @Override
    public ArrayAdapter<String> getColorsAdapter() {
        if (colorAccentSpinnerAdapter == null) {
            colorAccentSpinnerAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item,
                    NoteColorAccent.Colors.getColorStrings(context));

            colorAccentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

        return colorAccentSpinnerAdapter;
    }

    @Override
    public void onColorSpinnerItemSelected(int position) {
        NoteColorAccent.Colors selectedColor =
                NoteColorAccent.Colors.getColorFromString(context,
                        (String) colorAccentSpinnerAdapter.getItem(position));

        currentColor = selectedColor;

        noteEditor.changeColorAccent(currentColor.getColorResource());
    }

    @Override
    public void setupInitialColor(int colorId) {
        currentColor = NoteColorAccent.Colors.getFromId(colorId);
    }

    @Override
    public boolean hasSelectedColor() {
        return currentColor != null;
    }
}
