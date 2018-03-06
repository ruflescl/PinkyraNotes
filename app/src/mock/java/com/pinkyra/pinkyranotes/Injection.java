package com.pinkyra.pinkyranotes;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pinkyra.pinkyranotes.db.note.FakeNoteDao;
import com.pinkyra.pinkyranotes.db.note.FakeNoteRepo;
import com.pinkyra.pinkyranotes.db.note.NoteDao;
import com.pinkyra.pinkyranotes.util.FakeSharedPreferencesHelper;
import com.pinkyra.pinkyranotes.util.SharedPreferencesHelper;

/**
 * Injection class for providing true implementations of the app dependencies
 * <p>
 * TODO: Learn and replace this with a DI library (Dagger2)
 */
public class Injection {
    @NonNull
    public static SharedPreferencesHelper provideSharedPreferencesHelper(@NonNull Context appContext) {
        return new FakeSharedPreferencesHelper(appContext);
    }

    private static NoteDao provideDao_Note(@NonNull Context context) {
        return new FakeNoteDao();
    }

    static FakeNoteRepo noteRepository = null;
    public static FakeNoteRepo provideNoteRepository(@NonNull Context context) {
        if (noteRepository == null) noteRepository = new FakeNoteRepo(provideDao_Note(context));

        return noteRepository;
    }
}
