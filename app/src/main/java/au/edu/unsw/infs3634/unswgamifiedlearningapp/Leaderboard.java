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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Leaderboard extends AppCompatActivity implements LeaderboardAdapter.UserListener{
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    DatabaseAll mDb;

    //recycler adapter
    public static LeaderboardAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

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
                        startActivity(new Intent(Leaderboard.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Leaderboard.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Leaderboard.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Leaderboard.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Leaderboard.this, ProgressActivity.class));

                        break;

                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Leaderboard.this, Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        //set recycler view
        setLayout();
    }
    public void setLayout() {
        mDb = Room.databaseBuilder(getApplicationContext(), DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> users = (ArrayList<User>)mDb.userDao().getUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //set layout manager
                        layoutManager = new LinearLayoutManager(Leaderboard.this);
                        layoutManager.setOrientation(RecyclerView.VERTICAL);
                        //intiialise recycler adapter
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        //adapter = new LeaderboardAdapter(new ArrayList<User>());
                        adapter = new LeaderboardAdapter(users);
                        recyclerView.setAdapter(adapter);
                        //adapter.setUser(users);
                    }
                });

            }

        });

    }

    @Override
    public void onClick(int position) {
        System.out.println("clicked");
    }
}