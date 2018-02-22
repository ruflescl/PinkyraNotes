package com.pinkyra.pinkyranotes.notesoverview.notedetail.notecarddetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.pinkyra.pinkyranotes.util.SharedPreferencesHelper;

/**
 * Builder class for setting up card detail adapters to show in {@link com.pinkyra.pinkyranotes.notesoverview.NotesOverviewFragment}
 */
public class NoteCardDetailBuilder {
    public static void setupLayoutManager(
            @NonNull Context actContext,
            @NonNull RecyclerView view,
            @NonNull SharedPreferencesHelper.SharedPreferencesValues layoutType,
            @NonNull SharedPreferencesHelper.SharedPreferencesValues columnCount) {

        if (layoutType == SharedPreferencesHelper.SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__STAGGERED) {
            view.setLayoutManager(new StaggeredGridLayoutManager(parseColumnCount(columnCount), StaggeredGridLayoutManager.VERTICAL));
        } else if (layoutType == SharedPreferencesHelper.SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_STYLE__NORMAL) {
            view.setHasFixedSize(true);
            view.setLayoutManager(new GridLayoutManager(actContext, parseColumnCount(columnCount), GridLayoutManager.VERTICAL, false));
        }
    }

    @NonNull
    private static Integer parseColumnCount(SharedPreferencesHelper.SharedPreferencesValues columnCount) {
        if (columnCount == SharedPreferencesHelper.SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__ONE) {
            return 1;
        }
        if (columnCount == SharedPreferencesHelper.SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__TWO) {
            return 2;
        }
        if (columnCount == SharedPreferencesHelper.SharedPreferencesValues.NOTES_OVERVIEW__NOTE_DETAIL_COLUMN_COUNT__THREE) {
            return 3;
        }

        // Default value
        return 2;
    }
}
