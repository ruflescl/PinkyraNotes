package com.pinkyra.pinkyranotes.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.pinkyra.pinkyranotes.db.note.Note;
import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;
import com.pinkyra.pinkyranotes.db.note.NoteDao;

/**
 * The application database
 */
@Database(
        entities = {
                Note.class
        },
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Main database name
     */
    private static final String MAIN_DB_NAME = "main";

    /**
     * Database instance
     */
    private static AppDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, MAIN_DB_NAME)
                            .build();
        }
        return INSTANCE;
    }
}
