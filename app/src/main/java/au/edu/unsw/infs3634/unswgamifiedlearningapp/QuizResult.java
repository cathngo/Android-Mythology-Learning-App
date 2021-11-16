package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class QuizResult extends AppCompatActivity {
    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    Button btnShare;
    ProgressBar progressBar;
    TextView txtProgress;
    TextView txtLevel;
    TextView txtDesc;
    TextView txtDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        /**navigation menu code start**/
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this,  QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, ProgressActivity.class));
                        break;

                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this,  Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/



        //get the results
        Intent incomingIntent = getIntent();
        int numCorrect = incomingIntent.getIntExtra("Correct",0);
        int numIncorrect = incomingIntent.getIntExtra("Incorrect",0);
        String difficulty = incomingIntent.getStringExtra("Difficulty");

        double progress = (double)numCorrect /10 * 100;
        int new_prog = (int)progress;
        System.out.println("progress is" + progress);
        progressBar = findViewById(R.id.progressBar);
        txtProgress = findViewById(R.id.txtProgress);
        txtLevel = findViewById(R.id.txtLevel);
        txtDesc = findViewById(R.id.txtDesc);
        txtDifficulty = findViewById(R.id.txtDifficulty);

        txtDifficulty.setText("Quiz Difficulty: " + difficulty);

        progressBar.setProgress(new_prog);
        txtProgress.setText(String.valueOf(new_prog) + "%");
        txtLevel.setText(String.valueOf(numCorrect) + " / 10 ");

        if (numCorrect >= 0 && numCorrect  <= 4){
            txtDesc.setText("You got " + numCorrect + " out of 10 questions correct. You should go back to the learn page to revise the content!");
        } else if (numCorrect == 5) {
            txtDesc.setText("You got " + numCorrect + " out of 10 questions correct. Half way there!");
        } else if (numCorrect >= 6 && numCorrect  <= 9) {
            txtDesc.setText("Well done! You got " + numCorrect + " out of 10 questions correct. Not bad!");
        } else if (numCorrect == 10) {
            txtDesc.setText("Congratulations! You got " + numCorrect + " out of 10 questions correct. Full marks!");
        }

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "download this app";
                String Sub = "Checkout my quiz results for Myth! I got " + numCorrect + " out of 10 questions correct!";
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Share results with"));

            }
        });
    }



}