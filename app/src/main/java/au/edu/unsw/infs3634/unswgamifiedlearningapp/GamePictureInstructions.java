package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamePictureInstructions extends AppCompatActivity {
    /**This class includes the implementation of the Monster Match instruction page informing
     * the user how to play the game**/
    Button btnStartPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_picture_instructions);

        //Start the game if user selects start
        btnStartPic = findViewById(R.id.btnStartPic);
        btnStartPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GamePictureInstructions.this, GamePictureActivity.class));
            }

        });
    }
}