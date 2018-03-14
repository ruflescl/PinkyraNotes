package com.pinkyra.pinkyranotes.noteeditor;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pinkyra.pinkyranotes.R;
import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;
import com.pinkyra.pinkyranotes.noteeditor.coloraccentpicker.NoteEditorColorAccentPickerFragment;
import com.pinkyra.pinkyranotes.noteeditor.texteditor.NoteEditorTextFragment;

/**
 * Note editing activity (note creation and edition)
 */
public class NoteEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        if (savedInstanceState == null) {
            initFragments();
        }
    }

    private void initFragments() {
        NoteEditorColorAccentPickerFragment accentPickerFragment =
                NoteEditorColorAccentPickerFragment.newInstance(NoteColorAccent.Colors.ORANGE.getId());
        NoteEditorTextFragment noteEditorTextFragment = NoteEditorTextFragment.newInstance(null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.acne_fram_color_picker_frame, accentPickerFragment, NoteEditorColorAccentPickerFragment.TAG);
        transaction.add(R.id.acne_fram_note_edit_frame, noteEditorTextFragment, NoteEditorTextFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_note_editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.acne_menu_item_save:
                //TODO Chamar presenter
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        NoteEditorTextFragment noteEditorTextFragment =
                (NoteEditorTextFragment) getSupportFragmentManager().findFragmentById(R.id.acne_fram_note_edit_frame);

        if (noteEditorTextFragment.doCheckBackPressedRule()) super.onBackPressed();
    }
}
