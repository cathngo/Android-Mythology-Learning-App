package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

public class Notes extends AppCompatActivity {
    
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    

    //CURRENTLY USING SHAREDPREFERECNES FOR DATASTORAGE BUT WILL HAVE TO LINK WITH
    // DATABASE SOON, ALSO NEED TO CHANGE MENU TO LINK WITH SIDENAV RATHER THAN OPTIONS MENU

    //Source: https://www.youtube.com/watch?v=48EB4HeP1kI&fbclid=IwAR1ZZ8XrB1zuAcvkT3vG6Y1XsoJ6zH-TeWk0ZdwWUTCfUzLToSx6F2_Xle4



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);


        ListView notesList = findViewById(R.id.notesList);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("au.edu.unsw.infs3634.unswgamifiedlearningapp", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if(set == null){

        }else{
            notes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, notes);
        notesList.setAdapter(arrayAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotesEditor.class);
                startActivity(intent);

            }
        });


        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),NotesEditor.class);
                i.putExtra("noteID", position);
                startActivity(i);
            }
        });



        //long click to delete notes
        notesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                new AlertDialog.Builder(Notes.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);

                                //tell the Arrayadapter that the note has been deleted and then remove them from sharedpreferences
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("au.edu.unsw.infs3634.unswgamifiedlearningapp", Context.MODE_PRIVATE);

                                HashSet<String> noteSet = new HashSet(Notes.notes);
                                sharedPreferences.edit().putStringSet("notes",noteSet).apply();



                            }
                        })
                        .setNegativeButton("No", null)
                        .show();



                return true;
            }
        });




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.note_menu, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        super.onOptionsItemSelected(item);
//        if (item.getItemId() == R.id.add_note){
//            Intent intent = new Intent(getApplicationContext(), NotesEditor.class);
//            startActivity(intent);
//            return true;
//        }
//        return false;
//
//    }
}
