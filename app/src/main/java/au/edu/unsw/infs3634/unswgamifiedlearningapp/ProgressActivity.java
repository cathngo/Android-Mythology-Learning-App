package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.concurrent.Executors;



//reference https://www.youtube.com/watch?v=YsHHXg1vbcc&ab_channel=CodinginFlow

public class ProgressActivity extends AppCompatActivity {
    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    ProgressBar progressBar;
    TextView txtProgress;
    TextView txtLevel;
    TextView txtDesc;
    Button btnShare;

    FirebaseAuth mAuth;
    private DatabaseAll mDb;

    int progress;
    int level;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
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
                        Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this,  QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this, ProgressActivity.class));
                        break;

                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this,  Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(ProgressActivity.this, Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        /** progress bar **/
        //Get the signed in user's email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        if (user != null) {
            // user is signed in, show user data
            //Search database for user given email as key
            mDb = Room.databaseBuilder(getApplicationContext(), DatabaseAll.class, "database-all")
                    .fallbackToDestructiveMigration()
                    .build();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    progress =currentUser.getProgress();
                    level = currentUser.getLevel();
                    System.out.println("progress is " + String.valueOf(progress));
                    System.out.println("level is " + String.valueOf(level));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //set progress bar with user information
                            progressBar = findViewById(R.id.progressBar);
                            txtProgress = findViewById(R.id.txtProgress);
                            txtLevel = findViewById(R.id.txtLevel);
                            txtDesc = findViewById(R.id.txtDesc);

                            progressBar.setProgress(progress);
                            txtProgress.setText(String.valueOf(progress) + "%");
                            txtLevel.setText("Level "+ String.valueOf(level));
                            if (progress != 0) {
                                txtDesc.setText(String.valueOf(progress) +"% of level " + String.valueOf(level)+ " completed. Keep up the good work!");
                                message = "Check out my progress!" + " I have " + String.valueOf(progress) +"% of level " + String.valueOf(level)+ " completed.";
                            } else {
                                txtDesc.setText(String.valueOf(progress) +"% of level " + String.valueOf(level)+ " completed. Time to get started!");
                                message = "Check out my progress!" + " I have " + String.valueOf(progress) +"% of level " + String.valueOf(level)+ " completed.";
                            }

                        }
                    });
                }
            });
        }
        else {
            // user is signed out, show sign-in form
            // user is signed out, show sign-in form
            Toast.makeText(this, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
            System.out.println("User is null");
            startActivity(new Intent(ProgressActivity.this, Login.class));
        }



        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "download this app";
                String Sub = message;
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Share results with"));

            }
        });

    }


}