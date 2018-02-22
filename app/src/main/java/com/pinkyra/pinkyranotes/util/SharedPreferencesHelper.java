package com.pinkyra.pinkyranotes.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Util for easy access and management for {@link android.content.SharedPreferences} keys and values
 * for this app (with validation from 'isValueValid' method)
 */
public class SharedPreferencesHelper {
    protected static final String SHARED_PREFERENCE_FILENAME = "com.pinkyra.pinkyranotes.sharedprefs";

    public enum SharedPreferencesKeys {
        NOTES_OVERVIEW__NOTE_DETAIL_STYLE("NOTES_OVERVIEW__NOTE_DETAIL_STYLE",
                SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED,
                new SharedPreferencesValues[]{SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED,
                        SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__NORMAL}),

        NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT("NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT",
                SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO,
                new SharedPreferencesValues[]{SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__ONE,
                        SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO,
                        SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__THREE});

        SharedPreferencesKeys(@NonNull String key, @NonNull SharedPreferencesValues defaultValue,
                              @NonNull SharedPreferencesValues[] acceptedValues) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.acceptedValues = acceptedValues;
        }

        String key;
        private SharedPreferencesValues defaultValue;
        private SharedPreferencesValues[] acceptedValues;

        public String getKeyName() {
            return this.key;
        }

        public SharedPreferencesValues getDefaultValue() {
            return this.defaultValue;
        }

        public SharedPreferencesValues[] getAcceptedValues() {
            return acceptedValues;
        }

        @NonNull
        public Boolean isValueValid(@NonNull SharedPreferencesValues value) {
            if (value == null) return false;

            for (SharedPreferencesValues acceptedValue :
                    this.getAcceptedValues()) {
                if (acceptedValue == value) return true;
            }

            return false;
        }
    }

    /**
     * Accepted and expected values for each 'SharedPreferencesKeys' key
     */
    public enum SharedPreferencesValues {
        NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED("NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED"),
        NOTES_OVERVIEW__NOTE_DETAIL_STYLE__NORMAL("NOTES_OVERVIEW__NOTE_DETAIL_STYLE__NORMAL"),

        NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__ONE("NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__ONE"),
        NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO("NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO"),
        NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__THREE("NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__THREE"),;

        SharedPreferencesValues(String value) {
            this.value = value;
        }

        String value;

        public String getValue() {
            return value;
        }

        @Nullable
        public static SharedPreferencesValues parseFromString(@Nullable String value) {
            if (value == null || value.trim().isEmpty()) return null;

            for (SharedPreferencesValues spv : SharedPreferencesValues.values()) {
                if (spv.value.equals(value)) return spv;
            }

            return null;
        }
    }

    Context appContext;
    SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(@NonNull Context appContext) {
        this.appContext = appContext;
        this.sharedPreferences = this.appContext.getSharedPreferences(SHARED_PREFERENCE_FILENAME,
                this.appContext.MODE_PRIVATE);
    }

    @NonNull
    public SharedPreferencesValues getValue(@NonNull SharedPreferencesKeys key) {
        String value = this.sharedPreferences.getString(key.getKeyName(), key.getDefaultValue().getValue());
        SharedPreferencesValues spv = SharedPreferencesValues.parseFromString(value);

        if (spv == null || !key.isValueValid(spv)) return key.getDefaultValue();
        else return spv;
    }
}
