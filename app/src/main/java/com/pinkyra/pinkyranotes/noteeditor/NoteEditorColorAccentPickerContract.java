package com.pinkyra.pinkyranotes.noteeditor;

import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;

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

    }

    /**
     * 'Business to User' interaction contract
     */
    interface View {
        void changeColorAccent(NoteColorAccent.Colors color);
    }
}
