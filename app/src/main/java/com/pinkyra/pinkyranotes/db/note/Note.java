package com.pinkyra.pinkyranotes.db.note;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
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

    public boolean equalsOther(Note note) {
        if (this.id != note.id) return false;
        if (!this.title.equals(note.title)) return false;
        if (!this.content.equals(note.content)) return false;

        return true;
    }
}
