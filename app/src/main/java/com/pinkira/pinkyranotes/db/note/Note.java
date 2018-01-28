package com.pinkira.pinkyranotes.db.note;

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
}
