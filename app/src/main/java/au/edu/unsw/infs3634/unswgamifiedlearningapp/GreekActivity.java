package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class GreekActivity extends AppCompatActivity implements View.OnClickListener {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    CardView greekLearnCV, greekQuizCV, greekNotesCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greek);


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
                        startActivity(new Intent(GreekActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this,Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GreekActivity.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/


        greekLearnCV = (CardView) findViewById(R.id.greekLearnCV);
        greekLearnCV.setOnClickListener(this);
        greekQuizCV = (CardView) findViewById(R.id.greekQuizCV);
        greekQuizCV.setOnClickListener(this);
        greekNotesCV = (CardView) findViewById(R.id.greekNotesCV);
        greekNotesCV.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.greekLearnCV:
                intent = new Intent(GreekActivity.this, LearningGreek.class);
                startActivity(intent);
                break;
            case R.id.greekQuizCV:
                intent = new Intent(GreekActivity.this, TopicQuiz.class);
                intent.putExtra("Category", "Greek");
                startActivity(intent);
                break;
            case R.id.greekNotesCV:
                intent = new Intent(GreekActivity.this, NotesActivity.class);
                startActivity(intent);
                break;
        }


    }
}