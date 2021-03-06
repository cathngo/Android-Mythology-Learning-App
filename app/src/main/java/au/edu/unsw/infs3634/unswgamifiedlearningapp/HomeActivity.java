package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;


//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
//Reference for obtaining user details from firebase: https://firebase.google.com/docs/auth/web/manage-users
//Reference for search item: https://newbedev.com/android-edittext-finished-typing-event

public class HomeActivity extends AppCompatActivity
{
    /**This class includes the implementation of the home page the user enters upon login**/

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

    ProgressBar barEgyptian;
    ProgressBar barGreece;
    ProgressBar barRoman;
    TextView pcEgyptian;
    TextView pcGreece;
    TextView pcRoman;
    TextView txtQuiz;
    TextView txtMyth;
    TextView txtMonster;

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

        //Set on click listener to navigate to the page the user selected
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, ProgressActivity.class));
                        break;
                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, Leaderboard.class));
                        break;
                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        PageTracker.resetPageTracker();
                        startActivity(new Intent(HomeActivity.this, Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        //Reference: https://firebase.google.com/docs/auth/web/manage-users
        //Get the signed in user's email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //Search database for user given email as key
            mDb = Room.databaseBuilder(getApplicationContext(), DatabaseAll.class, "database-all")
                    .fallbackToDestructiveMigration()
                    .build();
            //Get user's email
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //Find the signed in user's current details
                    User currentUser = mDb.userDao().getUser(email);
                    String fName = currentUser.getFirstName();
                    String lName = currentUser.getLastName();
                    String name = fName + " " + lName;
                    String username = currentUser.getUsername();
                    String level = String.valueOf(currentUser.getLevel());

                    int egyptianProgress = currentUser.getEgyptianProgress();
                    int greekProgress = currentUser.getGreekProgress();
                    int romanProgress = currentUser.getRomanProgress();

                    int quizAttempts = currentUser.getQuizAttempts();

                    int mythScore = currentUser.getMythScore();
                    int monsterScore = currentUser.getMonsterScore();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Set the username, name and level textviews
                            txtLevelDisplay = findViewById(R.id.txtLevelDisplay);
                            txtNameDisplay = findViewById(R.id.txtNameDisplay);
                            txtUsernameDisplay = findViewById(R.id.txtUsernameDisplay);

                            txtLevelDisplay.setText("Level " + level);
                            txtNameDisplay.setText(name);
                            txtUsernameDisplay.setText(username);

                            //set the progress bars to reflect user's progress
                            barEgyptian = findViewById(R.id.barEgyptian);
                            barGreece = findViewById(R.id.barGreece);
                            barRoman = findViewById(R.id.barRoman);
                            pcEgyptian = findViewById(R.id.pcEgyptian);
                            pcGreece = findViewById(R.id.pcGreece);
                            pcRoman = findViewById(R.id.pcRoman);

                            //cap the progress at 100%
                            if (egyptianProgress >= 100) {
                                pcEgyptian.setText("100%");
                            } else {
                                pcEgyptian.setText(String.valueOf(egyptianProgress + "%"));
                            }

                            if (greekProgress >= 100) {
                                pcGreece.setText("100%");
                            } else {
                                pcGreece.setText(String.valueOf(greekProgress + "%"));
                            }

                            if (romanProgress >= 100) {
                                pcRoman.setText("100%");
                            } else{
                                pcRoman.setText(String.valueOf(romanProgress + "%"));
                            }

                            barEgyptian.setProgress(egyptianProgress);
                            barGreece.setProgress(greekProgress);
                            barRoman.setProgress(romanProgress);

                            //Set the textview to display the number of quiz attempts
                            txtQuiz = findViewById(R.id.txtQuiz);
                            txtQuiz.setText("Total Attempts: " + String.valueOf(quizAttempts) + " tries");

                            //Set the textview to display the number of quiz attempts
                            txtMonster = findViewById(R.id.txtMonster);
                            txtMyth = findViewById(R.id.txtMyth);

                            //-1 indicated the user has not yet played a game
                            //Therefore should set the score to N/A
                            if (mythScore == -1) {
                                txtMyth.setText("Myth Scramble: N/A");
                            } else {
                                //Otherwise, reflect the user's latest score
                                txtMyth.setText("Myth Scramble: " + mythScore + "/10");
                            }

                            //-1 indicated the user has not yet played a game
                            //Therefore should set the score to N/A
                            if (monsterScore == -1) {
                                txtMonster.setText("Monster Match: N/A");
                            } else {
                                //Otherwise, reflect the user's latest score
                                txtMonster.setText("Monster Match: "+monsterScore + "/5");
                            }

                        }
                    });
                }
            });
        }
        else {
            //User is signed out, show sign-in form
            Toast.makeText(this, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
            System.out.println("User is null");
            startActivity(new Intent(HomeActivity.this, Login.class));
        }

        //Create the internet search function
        instantiateSearch();
    }

    //Reference https://newbedev.com/android-edittext-finished-typing-event
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
                        // User has finished typing
                        String query = String.valueOf(searchText.getText());
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        //Search the internet with the user input
                        intent.putExtra(SearchManager.QUERY, query);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log the user out if the user is null
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(HomeActivity.this, Login.class));
        }
    }
}