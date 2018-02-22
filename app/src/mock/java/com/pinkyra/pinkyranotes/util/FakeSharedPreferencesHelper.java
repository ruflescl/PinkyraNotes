package com.pinkyra.pinkyranotes.util;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Fake implementation for testing (mock)
 */
public class FakeSharedPreferencesHelper extends SharedPreferencesHelper {
    public FakeSharedPreferencesHelper(@NonNull Context appContext) {
        super(appContext);
    }

    @NonNull
    public SharedPreferencesValues getValue(@NonNull SharedPreferencesKeys key) {
        if (key == SharedPreferencesKeys.NOTES_OVERVIEW__NOTE_DETAIL_STYLE) {
            return SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED;
        }
        if (key == SharedPreferencesKeys.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT) {
            return SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO;
        }

        return key.getDefaultValue();
    }
}
