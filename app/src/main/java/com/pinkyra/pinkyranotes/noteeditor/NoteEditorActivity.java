package com.pinkyra.pinkyranotes.noteeditor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.pinkyra.pinkyranotes.R;
import com.pinkyra.pinkyranotes.db.note.NoteColorAccent;

/**
 * Note editing activity (note creation and edition)
 */
public class NoteEditorActivity extends AppCompatActivity {
    View colorAccentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        setupViews();
    }

    private void setupViews() {
        colorAccentView = findViewById(R.id.cent_view_color_indicator);

        Spinner spinner = findViewById(R.id.cent_spin_color_indicator);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                NoteColorAccent.Colors.getColorStrings(this));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                NoteColorAccent.Colors selectedColor = NoteColorAccent.Colors.getColorFromString(NoteEditorActivity.this,
                        (String) adapterView.getSelectedItem());

                changeColorAccent(selectedColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void changeColorAccent(@NonNull NoteColorAccent.Colors selectedColor) {
        colorAccentView.setBackgroundResource(selectedColor.getColorResource());
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
}
