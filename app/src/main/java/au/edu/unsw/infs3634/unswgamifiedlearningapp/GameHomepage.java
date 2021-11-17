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

public class GameHomepage extends AppCompatActivity {
    CardView scrambleCV, monsterCV;
    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_homepage);
        /**navigation menu code start**/
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView nav = (NavigationView) findViewById(R.id.navmenu);
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
                        startActivity(new Intent(GameHomepage.this, HomeActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, Leaderboard.class));

                        break;
                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, Login.class));
                        break;
                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(GameHomepage.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        //making the cardviews clickable
        scrambleCV = (CardView) findViewById(R.id.scrambleCV);
        monsterCV = (CardView) findViewById(R.id.monsterCV);

        scrambleCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameHomepage.this, GameWordInstructions.class));
            }
        });
     monsterCV.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(GameHomepage.this, GamePictureInstructions.class));
         }
     });
    }

}