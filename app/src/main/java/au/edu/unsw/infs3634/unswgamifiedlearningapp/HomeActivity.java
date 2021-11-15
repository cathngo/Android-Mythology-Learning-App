package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity
{
    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    FirebaseAuth mAuth;
    TextView txtNameDisplay;
    TextView txtUsernameDisplay;
    TextView txtLevelDisplay;
    private DatabaseAll mDb;
    EditText searchText;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
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
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, Leaderboard.class));

                        break;
                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/


        //Get the signed in user's email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //Search database for user given email as key
            mDb = Room.databaseBuilder(getApplicationContext(), DatabaseAll.class, "database-all")
                    .fallbackToDestructiveMigration()
                    .build();
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    String fName = currentUser.getFirstName();
                    String lName = currentUser.getLastName();
                    String name = fName + " " + lName;
                    String username = currentUser.getUsername();
                    String level = String.valueOf(currentUser.getLevel());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //set the username, name and level textviews
                            txtLevelDisplay = findViewById(R.id.txtLevelDisplay);
                            txtNameDisplay = findViewById(R.id.txtNameDisplay);
                            txtUsernameDisplay = findViewById(R.id.txtUsernameDisplay);

                            txtLevelDisplay.setText("Level " + level);
                            txtNameDisplay.setText(name);
                            txtUsernameDisplay.setText(username);
                        }
                    });
                }
            });
        }
        else {
            // user is signed out, show sign-in form
            Toast.makeText(this, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
            System.out.println("User is null");
            startActivity(new Intent(HomeActivity.this, Login.class));
        }


        //search function
        instantiateSearch();


    }

    //reference https://newbedev.com/android-edittext-finished-typing-event
    public void instantiateSearch() {
        searchText = findViewById(R.id.searchText);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent != null &&
                                keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (keyEvent == null || !keyEvent.isShiftPressed()) {
                        // the user is done typing.
                        String query = String.valueOf(searchText.getText());
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, query); // query contains search string
                        startActivity(intent);
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }

        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(HomeActivity.this, Login.class));
        }
    }

}