package com.pinkira.pinkyranotes.db.note;

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
    List<Note> loadAllNotes();

    @Query("SELECT * FROM Note WHERE id = :id")
    List<Note> loadNoteFromId(@NonNull Long id);

    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    @Update(onConflict = REPLACE)
    void updateNote(Note note);
}
