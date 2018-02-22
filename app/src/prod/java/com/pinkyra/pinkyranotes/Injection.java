package com.pinkyra.pinkyranotes;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pinkyra.pinkyranotes.db.database.AppDatabase;
import com.pinkyra.pinkyranotes.db.note.NoteDao;
import com.pinkyra.pinkyranotes.util.SharedPreferencesHelper;

/**
 * Injection class for providing true implementations of the app dependencies
 * <p>
 * TODO: Learn and replace this with a DI library (Dagger2)
 */
public class Injection {
    @NonNull
    public static SharedPreferencesHelper provideSharedPreferencesHelper(@NonNull Context appContext) {
        return new SharedPreferencesHelper(appContext);
    }

    public static NoteDao provideDao_Note(@NonNull Context context) {
        return AppDatabase.getDatabase(context).noteDao();
    }
}
