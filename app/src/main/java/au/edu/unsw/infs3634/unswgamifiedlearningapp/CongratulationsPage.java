package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CongratulationsPage extends AppCompatActivity {

    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations_page);
        message = findViewById(R.id.txtMessage);
        Intent incomingIntent = getIntent();
        int level  = incomingIntent.getIntExtra("Level",0);
        message.setText("Congratulations! You have leveled up to level " +level+". Keep up the good work!");
    }
}