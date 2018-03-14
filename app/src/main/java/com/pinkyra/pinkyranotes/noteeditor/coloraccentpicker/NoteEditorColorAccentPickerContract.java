package com.pinkyra.pinkyranotes.noteeditor.coloraccentpicker;

import android.widget.ArrayAdapter;

/**
 * Contract interfaces for specifying interactions between  {@link NoteEditorColorAccentPickerFragment} and
 * {@link NoteEditorColorAccentPickerPresenter}
 */
public interface NoteEditorColorAccentPickerContract {

    /**
     * 'User to Business' interaction contract
     * <p>
     * Remember: Use 'use case' naming style when possible
     */
    interface UserActionsListener {
        void onViewsCreated();
        ArrayAdapter<String> getColorsAdapter();
        void onColorSpinnerItemSelected(int position);
        void setupInitialColor(int colorId);
        boolean hasSelectedColor();
    }

    /**
     * 'Business to User' interaction contract
     */
    interface View {
        void changeColorAccent(int colorId);
        void changeSpinnerPosition(final int position);
    }
}
