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

public class LearningOther extends AppCompatActivity implements View.OnClickListener {


    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    Button nextButton, previousButton;

    int stringIDList[] = {R.string.Otherlearn1, R.string.Otherlearn2, R.string.Otherlearn3, R.string.Otherlearn4, R.string.Otherlearn5, R.string.Otherlearn6};
    int stringListCounter = 0;
    int pictureIDList[] = {R.drawable.odin, R.drawable.thor_picture, R.drawable.vanir, R.drawable.japanese, R.drawable.mana, R.drawable.maui};


    TextView otherText;
    ImageView otherPicture;
    CardView otherLearningCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_other);

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
                        startActivity(new Intent(LearningOther.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        Toast.makeText(getApplicationContext(),"Learn Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this,LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        Toast.makeText(getApplicationContext(),"NotesPanel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this,NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        Toast.makeText(getApplicationContext(),"Quiz Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        Toast.makeText(getApplicationContext(),"Progress Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        Toast.makeText(getApplicationContext(),"Leaderboard Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(LearningOther.this,Login.class));
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


        otherText = (TextView) findViewById(R.id.otherText);
        otherPicture = (ImageView) findViewById(R.id.otherPicture);
        otherLearningCV = (CardView) findViewById(R.id.otherLearningCV);
        otherLearningCV.setOnClickListener(this);



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
            intent = new Intent(LearningOther.this, TopicQuiz.class);
            intent.putExtra("Category", "Other");
            startActivity(intent);

        }else if (id ==R.id.otherLearningCV){

            if(stringListCounter == 0 || stringListCounter == 1 || stringListCounter == 2){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worldhistory.org/Norse_Mythology/"));
                startActivity(intent);
            }else if(stringListCounter == 3){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.britannica.com/topic/Japanese-mythology"));
                startActivity(intent);
            }else if(stringListCounter == 4 || stringListCounter == 5){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mythencyclopedia.com/Pa-Pr/Polynesian-Mythology.html"));
                startActivity(intent);
            }






        }

        otherText.setText(stringIDList[stringListCounter]);
        otherPicture.setImageDrawable(getResources().getDrawable(pictureIDList[stringListCounter]));

    }
}