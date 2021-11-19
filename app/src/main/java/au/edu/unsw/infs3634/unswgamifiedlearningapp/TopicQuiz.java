package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
//Reference for some of Quiz logic: https://www.youtube.com/watch?v=vpT0eIUREC0&ab_channel=Learnoset-LearnCodingOnline
//Reference for navigation drawer: https://www.youtube.com/watch?v=TifpldOStWI&ab_channel=MdJamal
public class TopicQuiz extends AppCompatActivity implements View.OnClickListener {

    /** navigation menu **/
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    /** navigation menu **/

    String category;
    int questionCounter;
    TextView question;
    Button option1, option2, option3, option4, nextButton;
    List<TopicQuestion> questionsList = new ArrayList<>();
    int currentQuestionPosition = 0;
    String selectedOption = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_quiz);


        /**navigation menu code start**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, HomeActivity.class));
                        break;

                    case R.id.menu_learn:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, LearnActivity.class));
                        break;

                    case R.id.menu_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, NotesActivity.class));
                        break;

                    case R.id.menu_quiz:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, QuizActivity.class));
                        break;

                    case R.id.menu_progress:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, ProgressActivity.class));

                        break;
                    case R.id.menu_friends:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, Leaderboard.class));
                        break;

                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        PageTracker.resetPageTracker();
                        startActivity(new Intent(TopicQuiz.this, Login.class));
                        break;

                    case R.id.menu_game:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(TopicQuiz.this, GameHomepage.class));
                        break;
                }

                return true;
            }
        });
        /**navigation menu code end**/

        //get the intent from the learning screen that determines the category shown
        category = getIntent().getStringExtra("Category");
        Log.d("TopicQuiz", category);

        question = (TextView) findViewById(R.id.questionTV);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        nextButton = (Button) findViewById(R.id.nextQuestion);

        questionsList = TopicQuestion.getQuestions(category);
        Log.d("TopicQuiz", questionsList.get(1).getQuestion());


        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }


    //create the onClick method and make a case for each option
    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.option1:

                if(selectedOption.isEmpty()){

                    selectedOption = option1.getText().toString();

                    option1.setBackgroundColor(Color.RED);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);


                }

                break;

            case R.id.option2:

                if(selectedOption.isEmpty()){

                    selectedOption = option2.getText().toString();

                    option2.setBackgroundColor(Color.RED);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);



                }

                break;

            case R.id.option3:

                if(selectedOption.isEmpty()){


                    selectedOption = option3.getText().toString();

                    option3.setBackgroundColor(Color.RED);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);

                }

                break;

            case R.id.option4:

                if(selectedOption.isEmpty()){


                    selectedOption = option4.getText().toString();
                    option4.setBackgroundColor(Color.RED);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);

                }

                break;

            case R.id.nextQuestion:

                if(selectedOption.isEmpty()){
                    Toast.makeText(TopicQuiz.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }else{
                    option1.setClickable(true);
                    option2.setClickable(true);
                    option3.setClickable(true);
                    option4.setClickable(true);

                    changeNextQuestion();

                }

                break;



        }




    }


    //create method that will be called on the onClick method.
    //it changes the color of the correct answer and the wrong answer
    private void revealAnswer(){

        String answer = questionsList.get(currentQuestionPosition).getAnswer();

        if(option1.getText().toString().equals(answer)){
            option1.setBackgroundColor(Color.GREEN);
            option1.setTextColor(Color.BLACK);

        }else if (option2.getText().toString().equals(answer)){
            option2.setBackgroundColor(Color.GREEN);
            option2.setTextColor(Color.BLACK);

        }else if (option3.getText().toString().equals(answer)){
            option3.setBackgroundColor(Color.GREEN);
            option3.setTextColor(Color.BLACK);

        }else if (option4.getText().toString().equals(answer)){
            option4.setBackgroundColor(Color.GREEN);
            option4.setTextColor(Color.BLACK);

        }


    }


    //created a method that changes the question and resets the colors of the answer buttons
    //on the last page changes the button to "Submit"
    private void changeNextQuestion(){

        currentQuestionPosition++;

        if((currentQuestionPosition+1) ==questionsList.size()){
            nextButton.setText("Submit");

        }

        if(currentQuestionPosition < questionsList.size()){

            selectedOption = "";


            option1.setBackgroundColor(Color.parseColor("#DCD8D8"));
            option2.setBackgroundColor(Color.parseColor("#DCD8D8"));
            option3.setBackgroundColor(Color.parseColor("#DCD8D8"));
            option4.setBackgroundColor(Color.parseColor("#DCD8D8"));

            option1.setTextColor(Color.BLACK);
            option2.setTextColor(Color.BLACK);
            option3.setTextColor(Color.BLACK);
            option4.setTextColor(Color.BLACK);


            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
            option4.setText(questionsList.get(currentQuestionPosition).getOption4());


        //pass through the correct answers and the incorrect answers as an extra to the results page
        }else{
            Intent intent = new Intent(TopicQuiz.this, TopicQuizResults.class);
            intent.putExtra("Correct Answers", getCorrectAnswers());
            intent.putExtra("Incorrect Answers", getIncorrectAnswers());
            intent.putExtra("Incorrect Answers", getIncorrectAnswers());
            intent.putExtra("Category", category);

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            Log.d("correct", String.valueOf(getCorrectAnswers()));
            Log.d("incorrect", String.valueOf(getIncorrectAnswers()));

            finish();
        }

    }

    //create  a method that returns an integer of the amount of questions that were answered correctly
    private int getCorrectAnswers(){

        int correctAnswers = 0;

        for (int i=0; i<questionsList.size(); i++){

            String userSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            String answer = questionsList.get(i).getAnswer();

            if(userSelectedAnswer.equals(answer)){
                correctAnswers++;
            }

        }
        return correctAnswers;
    }


    //same method as above but for incorrect answers
    private int getIncorrectAnswers(){

        int incorrectAnswers = 0;

        for (int i=0; i<questionsList.size(); i++){

            String userSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            String answer = questionsList.get(i).getAnswer();

            if(!userSelectedAnswer.equals(answer)){
                incorrectAnswers++;
            }

        }
        return incorrectAnswers;
    }
}
