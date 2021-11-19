package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class LearningRoman extends AppCompatActivity implements View.OnClickListener {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/


    Button nextButton, previousButton;

    //make lists of that contain the string and image references of all the learning info/images

    int stringIDList[] = {R.string.Romanlearn1, R.string.Romanlearn2, R.string.Romanlearn3, R.string.Romanlearn4};
    int stringListCounter = 0;
    int pictureIDList[] = {R.drawable.jupiter, R.drawable.juno, R.drawable.neptune, R.drawable.romulus};


    TextView romanText;
    ImageView romanPicture;
    CardView romanLearningCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_roman);


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
                        startActivity(new Intent(LearningRoman.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningRoman.this,Login.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/



//check these next buttons
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(this);


        romanText = (TextView) findViewById(R.id.romanText);
        romanPicture = (ImageView) findViewById(R.id.romanPicture);
        romanLearningCV = (CardView) findViewById(R.id.romanLearningCV);
        romanLearningCV.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {



        Intent intent;
        int id = view.getId();

        //determine what the buttons do and say depending on page of learning


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
            intent = new Intent(LearningRoman.this, TopicQuiz.class);
            intent.putExtra("Category", "Roman");
            startActivity(intent);

<<<<<<< Updated upstream
        }else if (id ==R.id.greekLearningCV){
=======
            //cardView clicks through to specific information on the selected topic
        } else if (id == R.id.greekLearningCV) {
>>>>>>> Stashed changes

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nationalgeographic.org/article/gods-and-goddesses-ancient-rome/"));
            startActivity(intent);


        }

        romanText.setText(stringIDList[stringListCounter]);
        romanPicture.setImageDrawable(getResources().getDrawable(pictureIDList[stringListCounter]));



        //add progress to the specific topic for the user which is displayed on the dashboard
        Context context = getApplicationContext();
        if (stringListCounter == 1) {
            //mark the first page as completed
            if (PageTracker.romanOne == false) {
                PageTracker.romanOne = true;
                //add progress to greek module by 25%
                LevelUp.increaseRomanProgress(context, 25);
            }
        } else if (stringListCounter == 2) {
            if (PageTracker.romanTwo == false) {
                PageTracker.romanTwo = true;
                //add progress to greek module by 25%
                LevelUp.increaseRomanProgress(context, 25);
            }

        } else if (stringListCounter == 3) {
            if (PageTracker.romanThree == false) {
                PageTracker.romanThree = true;
                LevelUp.increaseRomanProgress(context, 25);
            }

        } else if (stringListCounter == 4) {

            if (PageTracker.romanFour == false) {
                PageTracker.romanFour = true;
                LevelUp.increaseRomanProgress(context, 25);
            }

        }



    }
}