package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;


//Source: https://www.youtube.com/watch?v=48EB4HeP1kI&fbclid=IwAR1ZZ8XrB1zuAcvkT3vG6Y1XsoJ6zH-TeWk0ZdwWUTCfUzLToSx6F2_Xle4

public class NotesEditor extends AppCompatActivity {
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);

        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID",-1);


        //work out whether a new note or an old note (editnotes)
        if (noteID != -1){

            editText.setText(NotesActivity.notes.get(noteID));
        } else {
            NotesActivity.notes.add("");
            noteID = NotesActivity.notes.size() - 1;
            NotesActivity.arrayAdapter.notifyDataSetChanged();
        }



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                NotesActivity.notes.set(noteID,String.valueOf(s));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("au.edu.unsw.infs3634.unswgamifiedlearningapp", Context.MODE_PRIVATE);

                HashSet<String> noteSet = new HashSet(NotesActivity.notes);
                sharedPreferences.edit().putStringSet("notes",noteSet).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}