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

    TextView resultsStatement, resultsNumber;

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


        resultsNumber = (TextView) findViewById(R.id.resultsNumerals);
        resultsStatement = (TextView) findViewById(R.id.resultsStatement);


        switch (correct){
            case "0":
                resultsNumber.setText("0");
                break;
            case "1":
                resultsNumber.setText("I");
                break;
            case "2":
                resultsNumber.setText("II");
                break;
            case "3":
                resultsNumber.setText("III");
                break;
            case "4":
                resultsNumber.setText("IV");
                break;
            case "5":
                resultsNumber.setText("V");
                break;
            case "6":
                resultsNumber.setText("VI");
                break;
            case "7":
                resultsNumber.setText("VII");
                break;
            case "8":
                resultsNumber.setText("VIII");
                break;
        }


        if(correctAnswers == 1 && incorrectAnswers != 1){
            resultsStatement.setText("You answered " +correct +" question correctly and made " + incorrect + " mistakes");

        }else if (correctAnswers != 1 && incorrectAnswers == 1){
            resultsStatement.setText("You answered " +correct +" questions correctly and made " + incorrect + " mistake");

        }else if (correctAnswers != 1 && incorrectAnswers != 1) {
            resultsStatement.setText("You answered " + correct + " questions correctly and made " + incorrect + " mistakes");

        }

        }
}