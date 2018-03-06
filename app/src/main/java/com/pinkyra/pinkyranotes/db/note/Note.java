package com.pinkyra.pinkyranotes.db.note;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

/**
 * 'Note' entity
 */
@Entity
public class Note {
    @PrimaryKey
    @NonNull
    public long id;

    @NonNull
    public String title;

    @NonNull
    public String content;

    @NonNull
    @TypeConverters({NoteColorAccent.class})
    public NoteColorAccent.Colors color_accent;

    public boolean equalsOther(Note note) {
        if (this.id != note.id) return false;
        if (!this.title.equals(note.title)) return false;
        if (!this.content.equals(note.content)) return false;
        if (!this.color_accent.equals(note.color_accent)) return false;

        return true;
    }
}
