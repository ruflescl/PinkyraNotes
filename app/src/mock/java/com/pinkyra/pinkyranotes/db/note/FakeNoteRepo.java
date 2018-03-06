package com.pinkyra.pinkyranotes.db.note;

/**
 * Fake implementation for testing (mock)
 */
public class FakeNoteRepo extends NoteRepository {
    public FakeNoteRepo(NoteDao localRepo) {
        super(localRepo);
    }
}
