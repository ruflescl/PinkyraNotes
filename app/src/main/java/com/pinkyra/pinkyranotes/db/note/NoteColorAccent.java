package com.pinkyra.pinkyranotes.db.note;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import com.pinkyra.pinkyranotes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Color accent for note visualization (purely cosmetical)
 */
public class NoteColorAccent {

    public static final Colors DEFAULT_COLOR = Colors.ORANGE;

    public enum Colors {
        RED(1, R.color.noteAccent_Red, R.string.string_snca_label_color_red),
        PURPLE(2, R.color.noteAccent_Purple, R.string.string_snca_label_color_purple),
        BLUE(3, R.color.noteAccent_Blue, R.string.string_snca_label_color_blue),
        GREEN(4, R.color.noteAccent_Green, R.string.string_snca_label_color_green),
        YELLOW(5, R.color.noteAccent_Yellow, R.string.string_snca_label_color_yellow),
        ORANGE(6, R.color.noteAccent_Orange, R.string.string_snca_label_color_orange),
        BLACK(7, R.color.noteAccent_Black, R.string.string_snca_label_color_black),
        ;

        private int id;
        private int colorResource;
        private int stringResource;

        Colors(@NonNull int id, @NonNull int colorResource, @NonNull int stringResource) {
            this.id = id;
            this.colorResource = colorResource;
            this.stringResource = stringResource;
        }

        public int getId() {
            return id;
        }

        public int getColorResource() {
            return colorResource;
        }

        public int getStringResource() {
            return stringResource;
        }

        public static Colors getFromId(int id) {
            for (Colors item : Colors.values()) {
                if (item.getId() == id) {
                    return item;
                }
            }

            return DEFAULT_COLOR;
        }

        public static List<Integer> getColorStrings() {
            List<Integer> result = new ArrayList<>();

            for (Colors item : Colors.values()) {
                result.add(item.getStringResource());
            }

            return result;
        }
    }

    @TypeConverter
    public int fromColorAccent(NoteColorAccent.Colors noteAccentColor) {
        if (noteAccentColor == null) {
            return DEFAULT_COLOR.getId();
        }

        return noteAccentColor.getId();
    }

    @TypeConverter
    public NoteColorAccent.Colors toColorAccent(int noteAccentColorId) {
        return Colors.getFromId(noteAccentColorId);
    }
}