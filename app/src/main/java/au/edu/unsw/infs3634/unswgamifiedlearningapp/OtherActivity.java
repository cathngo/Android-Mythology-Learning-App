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

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {


    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    CardView otherLearnCV, otherQuizCV, otherNotesCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


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
                        startActivity(new Intent(OtherActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(OtherActivity.this,Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/


        otherLearnCV = (CardView) findViewById(R.id.otherLearnCV);
        otherLearnCV.setOnClickListener(this);
        otherQuizCV = (CardView) findViewById(R.id.otherQuizCV);
        otherQuizCV.setOnClickListener(this);
        otherNotesCV = (CardView) findViewById(R.id.otherNotesCV);
        otherNotesCV.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.otherLearnCV:
                intent = new Intent(OtherActivity.this, LearningOther.class);
                startActivity(intent);
                break;
            case R.id.otherQuizCV:
                intent = new Intent(OtherActivity.this, TopicQuiz.class);
                intent.putExtra("Category", "Other");
                startActivity(intent);
                break;
            case R.id.otherNotesCV:
                intent = new Intent(OtherActivity.this, NotesActivity.class);
                startActivity(intent);
                break;
        }

    }
}