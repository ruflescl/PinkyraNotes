package com.pinkyra.pinkyranotes.db.note;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Fake implementation for testing (mock)
 */
public class FakeNoteDao implements NoteDao {

    private List<Note> noteRepo;
    private MutableLiveData<List<Note>> noteLiveData;

    public FakeNoteDao() {
        noteRepo = initFakeRepo(false);
        noteLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Note>> loadAllNotes() {
        noteLiveData.postValue(noteRepo);
        return noteLiveData;
    }

    @Override
    public Note loadNoteFromId(@NonNull final Long id) {
        /* API LVL 24 ;_;
         return noteRepo.stream()
         .filter(note -> note.id == id)
         .collect(Collectors.toList());
         */

        for (Note note : noteRepo) {
            if (note.id == id) {
                return note;
            }
        }

        return null;
    }

    @Override
    public void insertNote(Note note) {
        noteRepo.add(note);
    }

    @Override
    public void updateNote(Note note) {
        if (note == null) return;

        List<Note> result = new ArrayList<>();

        for (Note item : noteRepo) {
            if (item.id == note.id) {
                item.title = note.title;
                item.content = note.content;
            }
        }
    }

    private List<Note> initFakeRepo(boolean emptyRepo) {
        List<Note> result = new ArrayList<>();

        if (!emptyRepo) {
            result.add(createShortNote(1L));
            result.add(createMediumNote(2L));
            result.add(createLongNote(3L));
            result.add(createMediumNote(4L));
            result.add(createShortNote(5L));
            result.add(createShortNote(6L));
            result.add(createShortNote(7L));
            result.add(createMediumNote(8L));
            result.add(createMediumNote(9L));
            result.add(createMediumNote(10L));
            result.add(createMediumNote(11L));
            result.add(createMediumNote(12L));
            result.add(createMediumNote(13L));
            result.add(createLongNote(14L));
        }

        return result;
    }

    private Note createNote(long id, String title, String content) {
        Note result = new Note();

        result.id = id;
        result.title = title;
        result.content = content;

        return result;
    }

    private Note createShortNote(long id) {
        return createNote(id, String.valueOf(id) + " Short Title", "Short Content");
    }

    private Note createMediumNote(long id) {
        return createNote(id, String.valueOf(id) + " Medium Title Medium Title", "Medium Content Medium Content Medium Content");
    }

    private Note createLongNote(long id) {
        return createNote(id, String.valueOf(id) + " Long Title Long Title Long Title", "Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content Long Content");
    }
}
