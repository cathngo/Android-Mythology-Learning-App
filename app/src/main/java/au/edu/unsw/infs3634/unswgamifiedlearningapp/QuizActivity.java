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
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
public class QuizActivity extends AppCompatActivity {
    /**This class includes the implementation of the Quiz homepage where the user selected the
     * difficulty level they would like to be tested on**/

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    FirebaseAuth mAuth;

    RadioButton rEasy;
    RadioButton rMedium;
    RadioButton rHard;
    Button btnStartQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mAuth = FirebaseAuth.getInstance();

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
                        startActivity(new Intent(QuizActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this,  NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this,  QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this, ProgressActivity.class));

                        break;

                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this,  Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        PageTracker.resetPageTracker();
                        startActivity(new Intent(QuizActivity.this,  Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(QuizActivity.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/



        //Radio buttons
        rEasy = findViewById(R.id.ansOne);
        rMedium = findViewById(R.id.ansTwo);
        rHard = findViewById(R.id.ansThree);

        //Check if user pressed start button
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check which radio button the user selected for level of difficulty
                //Then pass the difficulty level as an intent to the quiz class
                //Then switch pages to the quiz start page
                if (rEasy.isChecked()) {
                    System.out.println("easy selected");
                    Intent intent = new Intent(QuizActivity.this, QuizStart.class);
                    intent.putExtra("Difficulty", "Easy");
                    startActivity(intent);
                } else if (rMedium.isChecked()) {
                    System.out.println("medium selected");
                    Intent intent = new Intent(QuizActivity.this, QuizStart.class);
                    intent.putExtra("Difficulty", "Medium");
                    startActivity(intent);
                } else if (rHard.isChecked()){
                    System.out.println("hard selected");
                    Intent intent = new Intent(QuizActivity.this, QuizStart.class);
                    intent.putExtra("Difficulty", "Hard");
                    startActivity(intent);
                } else {
                    //Alert user has not selected a diffiulty level
                    Toast.makeText(QuizActivity.this, "Please select a difficulty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}