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

public class RomanActivity extends AppCompatActivity implements View.OnClickListener {


    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    CardView romanLearnCV, romanQuizCV, romanNotesCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roman);



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
                        startActivity(new Intent(RomanActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        PageTracker.resetPageTracker();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this,Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(RomanActivity.this, GameHomepage.class));
                        break;
                }



                return true;
            }
        });
        /**navigation menu code end**/



        romanLearnCV = (CardView) findViewById(R.id.romanLearnCV);
        romanLearnCV.setOnClickListener(this);
        romanQuizCV = (CardView) findViewById(R.id.romanQuizCV);
        romanQuizCV.setOnClickListener(this);
        romanNotesCV = (CardView) findViewById(R.id.romanNotesCV);
        romanNotesCV.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {


        Intent intent;

        switch (view.getId()){
            case R.id.romanLearnCV:
                intent = new Intent(RomanActivity.this, LearningRoman.class);
                startActivity(intent);
                break;
            case R.id.romanQuizCV:
                intent = new Intent(RomanActivity.this, TopicQuiz.class);
                intent.putExtra("Category", "Roman");
                startActivity(intent);
                break;
            case R.id.romanNotesCV:
                intent = new Intent(RomanActivity.this, NotesActivity.class);
                startActivity(intent);
                break;
        }



    }
}