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
//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
public class EgyptianActivity extends AppCompatActivity implements View.OnClickListener {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    CardView egyptLearnCV, egyptQuizCV, egyptNotesCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyptian);


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
                        startActivity(new Intent(EgyptianActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        PageTracker.resetPageTracker();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this,Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(EgyptianActivity.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/


        egyptLearnCV = (CardView) findViewById(R.id.egyptLearnCV);
        egyptLearnCV.setOnClickListener(this);
        egyptQuizCV = (CardView) findViewById(R.id.egyptQuizCV);
        egyptQuizCV.setOnClickListener(this);
        egyptNotesCV = (CardView) findViewById(R.id.egyptNotesCV);
        egyptNotesCV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.egyptLearnCV:
                intent = new Intent(EgyptianActivity.this, LearningEgyptian.class);
                startActivity(intent);
                break;
            case R.id.egyptQuizCV:
                intent = new Intent(EgyptianActivity.this, TopicQuiz.class);
                intent.putExtra("Category", "Egyptian");
                startActivity(intent);
                break;
            case R.id.egyptNotesCV:
                intent = new Intent(EgyptianActivity.this, NotesActivity.class);
                startActivity(intent);
                break;
        }


    }
}