package com.pinkyra.pinkyranotes.db.note;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Access Data Object for 'Note' entity
 */
@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> loadAllNotes(); // TODO: Use LiveData here (and fix the fake repo :) )

    @Query("SELECT * FROM Note WHERE id = :id")
    Note loadNoteFromId(@NonNull Long id);

    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    @Update(onConflict = REPLACE)
    void updateNote(Note note);
}
