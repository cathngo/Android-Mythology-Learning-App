package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

import java.util.logging.Level;
//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
public class TopicQuizResults extends AppCompatActivity {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    TextView resultsStatement, resultsNumber, textPercent;
    ProgressBar topicQuizProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_quiz_results);

        /**navigation menu code start**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        PageTracker.resetPageTracker();
                        startActivity(new Intent(TopicQuizResults.this, Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuizResults.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        Integer correctAnswers = getIntent().getIntExtra("Correct Answers", 0);
        Integer incorrectAnswers = getIntent().getIntExtra("Incorrect Answers", 0);
        String category = getIntent().getStringExtra("Category");

        //Increase module progress based on category to 100% as user now finished the learning module
        Context context = getApplicationContext();
        if (category.equals("Greek")) {
            if (PageTracker.greekQuiz == false) {
                PageTracker.greekQuiz = true;
                LevelUp.increaseGreekProgress(context, 25);
            }

        } else if (category.equals("Egyptian")) {
            if (PageTracker.egyptianQuiz == false) {
                LevelUp.increaseEgyptianProgress(context, 20);
            }

        } else if (category.equals("Roman")) {
            if (PageTracker.romanQuiz == false) {
                LevelUp.increaseRomanProgress(context, 25);
            }
        }
        String correct = String.valueOf(correctAnswers);
        String incorrect = String.valueOf(incorrectAnswers);
        Double percent =  (correctAnswers/ (double) (correctAnswers+incorrectAnswers));


        resultsNumber = (TextView) findViewById(R.id.textResult);
        resultsStatement = (TextView) findViewById(R.id.txtDesc2);


        double progress = (double)correctAnswers /8 * 100;
        int new_prog = (int)progress;

        topicQuizProgressBar = findViewById(R.id.topicQuizProgressBar);
        textPercent = findViewById(R.id.textPercent);

        topicQuizProgressBar.setProgress(new_prog);
        textPercent.setText(String.valueOf(new_prog) + "%");


        resultsNumber.setText(correct + " / 8 ");

        if (correctAnswers >= 0 && correctAnswers  <= 4){
            resultsStatement.setText("You got " + correct + " out of 8 questions correct. You should go back to the learn page to revise the content!");
        } else if (correctAnswers == 5) {
            resultsStatement.setText("You got " + correct + " out of 8 questions correct. Half way there!");
        } else if (correctAnswers >= 6 && correctAnswers  <= 9) {
            resultsStatement.setText("Well done! You got " + correct + " out of 8 questions correct. Not bad!");
        } else if (correctAnswers == 10) {
            resultsStatement.setText("Congratulations! You got " + correct + " out of 8 questions correct. Full marks!");
        }
        
        Button learnButton = findViewById(R.id.button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicQuizResults.this, LearnActivity.class);
                startActivity(intent);
            }
        });





        }
}