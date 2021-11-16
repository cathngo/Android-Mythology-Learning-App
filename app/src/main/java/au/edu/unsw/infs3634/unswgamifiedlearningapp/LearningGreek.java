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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class LearningGreek extends AppCompatActivity implements View.OnClickListener {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    Button nextButton, previousButton;

    int stringIDList[] = {R.string.Greeklearn1, R.string.Greeklearn2, R.string.Greeklearn3, R.string.Greeklearn4};
    int stringListCounter = 0;
    int pictureIDList[] = {R.drawable.zeus, R.drawable.dionysus, R.drawable.demetor, R.drawable.apollo_picture};


    TextView greekText;
    ImageView greekPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_greek);


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
                        startActivity(new Intent(LearningGreek.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningGreek.this,Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/



        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(this);
        greekText = (TextView) findViewById(R.id.greekText);
        greekPicture = (ImageView) findViewById(R.id.greekPicture);



    }

    @Override
    public void onClick(View view) {


        Intent intent;
        int id = view.getId();



        if(id == R.id.nextButton && stringListCounter < stringIDList.length - 1){
            stringListCounter++;

            if(stringListCounter == stringIDList.length - 1){
                nextButton.setText("Go to Quiz");
            }


        } else if (id == R.id.previousButton && stringListCounter > 0) {
            stringListCounter--;

            if (stringListCounter < stringIDList.length) {
                nextButton.setText("Next");
            }
        }else if (id == R.id.nextButton && stringListCounter == stringIDList.length - 1){
            intent = new Intent(LearningGreek.this, TopicQuiz.class);
            startActivity(intent);


        }

        greekText.setText(stringIDList[stringListCounter]);
        greekPicture.setImageDrawable(getResources().getDrawable(pictureIDList[stringListCounter]));

    }
}