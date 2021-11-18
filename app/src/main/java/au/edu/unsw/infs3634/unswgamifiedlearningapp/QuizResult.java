package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
//Reference for share feature: https://www.youtube.com/watch?v=i41rmT-GDXc&t=428s&ab_channel=MASKCourses
public class QuizResult extends AppCompatActivity {
    /**This class includes the implementation of the Quiz Result page which displays the results
     * of the user for both quizzes and games**/

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
    Button btnProg;
    TextView txtExp;

    //Global variables to keep track of the user's current level, number of correct
    //answers from the quiz/game played, and the difficulty selected
    int currentLevel;
    int numCorrect;
    String difficulty;

    //Game represents the quiz/game the user has played
    //0 represents the user completed the quiz
    //1 represents the user played the Myth Scramble game
    //2 representes the user played the Monster Match game
    int game = 0;

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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this,  QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, ProgressActivity.class));
                        break;

                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this,  Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizResult.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/


        //get the results
        Intent incomingIntent = getIntent();
        numCorrect = incomingIntent.getIntExtra("Correct",0);
        difficulty = incomingIntent.getStringExtra("Difficulty");
        game = incomingIntent.getIntExtra("Game",0);

        //Check if user completed a quiz
        if (game == 0) {
            //increase quiz attempts for that user in the room database
            Context context = getApplicationContext();
            LevelUp.increaseQuizAttempts(context);
        }

        //Check if user played Myth Scramble
        if (game == 1) {
            //Save their score and level user up
            Context context = getApplicationContext();
            LevelUp.saveMythScore(context, numCorrect);

        //Check if user played Monster Match
        } else if (game == 2) {
            //Save their score and level user up
            Context context = getApplicationContext();
            LevelUp.saveMonsterScore(context, numCorrect);
        }

        txtExp = findViewById(R.id.txtExp);
        //Add experience to the user and determine the experience points based on level of difficulty selected
        if (difficulty.equals("Easy")) {
            checkIfLeveledUp(25);
            txtExp.setText("You've earned 25 experience points! Check out your progress on your level or share your quiz results with others!");
        } else if (difficulty.equals("Medium")) {
            checkIfLeveledUp(50);
            txtExp.setText("You've earned 50 experience points! Check out your progress on your level or share your quiz results with others!");
        } else {
            checkIfLeveledUp(75);
            txtExp.setText("You've earned 75 experience points! Check out your progress on your level or share your quiz results with others!");
        }

        //Find the user's current progress on their level with the added points and display information on the progress bar
        double progress = (double)numCorrect /10 * 100;
        int new_prog = (int)progress;

        progressBar = findViewById(R.id.progressBar);
        txtProgress = findViewById(R.id.txtProgress);
        txtLevel = findViewById(R.id.txtLevel);
        txtDesc = findViewById(R.id.txtDesc);
        txtDifficulty = findViewById(R.id.txtDifficulty);
        progressBar.setProgress(new_prog);
        txtProgress.setText(String.valueOf(new_prog) + "%");

        //Set the textview based on the quiz or game completed
        if (game == 1) {
            txtDifficulty.setText("Game: Myth Unscramble");
        } else if (game == 2){
            txtDifficulty.setText("Game: Monster Match");
        } else {
            txtDifficulty.setText("Quiz Difficulty: " + difficulty);
        }

        if (game == 2) {
            txtLevel.setText(String.valueOf(numCorrect) + " / 5 ");
        } else {
            txtLevel.setText(String.valueOf(numCorrect) + " / 10 ");
        }

        //Set the custom message based on the game/quiz played and the score the user achieved
        if (game == 2) {
            if (numCorrect >= 0 && numCorrect  <= 4){
                txtDesc.setText("You got " + numCorrect + " out of 5 questions correct. You should go back to the learn page to revise the content!");
            } else if (numCorrect == 5) {
                txtDesc.setText("You got " + numCorrect + " out of 5 questions correct. Half way there!");
            } else if (numCorrect >= 6 && numCorrect  <= 9) {
                txtDesc.setText("Well done! You got " + numCorrect + " out of 5 questions correct. Not bad!");
            } else if (numCorrect == 10) {
                txtDesc.setText("Congratulations! You got " + numCorrect + " out of 5 questions correct. Full marks!");
            }
        } else {
            if (numCorrect >= 0 && numCorrect  <= 4){
                txtDesc.setText("You got " + numCorrect + " out of 10 questions correct. You should go back to the learn page to revise the content!");
            } else if (numCorrect == 5) {
                txtDesc.setText("You got " + numCorrect + " out of 10 questions correct. Half way there!");
            } else if (numCorrect >= 6 && numCorrect  <= 9) {
                txtDesc.setText("Well done! You got " + numCorrect + " out of 10 questions correct. Not bad!");
            } else if (numCorrect == 10) {
                txtDesc.setText("Congratulations! You got " + numCorrect + " out of 10 questions correct. Full marks!");
            }

        }

        //Check if user selected share option
        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create intent to share message with other applications
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "download this app";
                String Sub = "";
                //Create custom share message based on quiz/game played and score achieved
                if (game == 0) {
                    Sub = "Checkout my quiz results for Myth! I got " + numCorrect + " out of 10 questions correct!";
                } else if (game == 1) {
                    Sub = "Checkout my score for Myth Scramble! I got " + numCorrect + " out of 10 questions correct!";
                } else if (game == 2) {
                    Sub = "Checkout my score for Monster Match! I got " + numCorrect + " out of 5 questions correct!";
                }

                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Share results with"));

            }
        });

        //Check if user selected to view their progress
        btnProg = findViewById(R.id.btnProg);
        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take user to the progress page
                startActivity(new Intent(QuizResult.this, ProgressActivity.class));
            }
        });



    }

    //Helper method which checks if the user has leveled up with the additional points given
    //to them after completing a game/quiz. If they have, takes them to a congraulations page
    public void checkIfLeveledUp(int increase) {
        Context context = getApplicationContext();

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int currProgress = currentUser.getProgress();
                    int currLevel = currentUser.getLevel();
                    int newProg = currProgress + increase;

                    //Computer the user's new level
                    double newLevel = Math.floor((double)newProg/100.0);
                    int inputLevel = (int)newLevel;
                    int leveled = inputLevel + currLevel;

                    //If the user has levelled up, take them to the congratulations page
                    if (inputLevel > 0) {
                        Intent intent = new Intent(QuizResult.this, CongratulationsPage.class);
                        intent.putExtra("Level", leveled);
                        startActivity(intent);
                    }
                    LevelUp.lvlUserUp(context, increase);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }
}

