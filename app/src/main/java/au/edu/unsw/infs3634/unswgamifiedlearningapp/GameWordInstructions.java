package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameWordInstructions extends AppCompatActivity {
    /**This class includes the implementation of the Myth Scramble instruction page informing
     * the user how to play the game**/
    Button btnStartWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_word_instructions);
        btnStartWord = findViewById(R.id.btnStartWord);
        //Start the game if user selects start
        btnStartWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameWordInstructions.this, GameWordActivity.class));
            }
        });
    }
}