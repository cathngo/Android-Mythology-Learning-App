package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Reference for RetroFit API:  Week 6 and 7 Tutorial Covid Tracker: https://github.com/INFS3634/Covid19Tracker
//Reference for html escape: https://stackoverflow.com/questions/6502759/how-to-strip-or-escape-html-tags-in-android
public class QuizStart extends AppCompatActivity {
    /**This class includes the implementation of the quiz feature, which tests the user on questions
     * retrieved from OpenTrivia API call based on the level of difficulty the user selected prior**/

    TextView txtQuestion;
    RadioButton ansOne;
    RadioButton ansTwo;
    RadioButton ansThree;
    RadioButton ansFour;
    Button btnNxt;
    TextView txtTitle;
    TextView txtChoose;

    //List which contains the answers
    List<String> answers;
    //List which contains the results from the API call
    List<Result> results;
    //The current question
    Result curr;
    //Number of questions correct/incorrect
    int correct;
    int incorrect;

    //Boolean values to check if the user has selected or submitted an answer
    boolean selected = false;
    boolean submit = false;

    TextView txtCorrectAns;
    Button btnSubmit;

    //Global variable to keep track of the current question
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        /**retrofit api call to get open trivia questions**/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuestionService service = retrofit.create(QuestionService.class);

        //Check what difficulty user selected
        Intent incomingIntent = getIntent();
        String difficulty = incomingIntent.getStringExtra("Difficulty");
        Call<Question> responseCall;
        //Call the api to retrieve the questions based on the difficulty the user selected
        if (difficulty.equals("Easy")) {
            responseCall = service.getEasyQuestions();
        } else if (difficulty.equals("Medium")) {
            responseCall = service.getMediumQuestions();
        } else {
            responseCall = service.getHardQuestions();
        }

        //Enqueue the response
        responseCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                //If call was successful, get the questions and set the information based on the
                // retrieved question
                Question question = response.body();
                results = question.getResults();
                txtTitle = findViewById(R.id.txtTitle);
                txtChoose = findViewById(R.id.txtChoose);
                txtChoose.setText("Please select your answer:");
                int num = counter + 1;
                txtTitle.setText("Question " + num);

                //set textviews
                txtQuestion = findViewById(R.id.txtQuestion);
                ansOne = findViewById(R.id.ansOne);
                ansTwo = findViewById(R.id.ansTwo);
                ansThree = findViewById(R.id.ansThree);
                ansFour = findViewById(R.id.ansFour);

                //get curr question
                curr = results.get(counter);
                //Get rid of any html symbols
                String questionHtml = curr.getQuestion();
                String htmlEscape = Jsoup.parse(questionHtml).text();
                //Set the clean text without html symbols
                txtQuestion.setText(htmlEscape);

                //get all the answers to the question
                answers =  curr.getIncorrectAnswers();
                String correctAns = curr.getCorrectAnswer();
                answers.add(correctAns);
                //shuffle list of answers to randomise where correct answer is
                Collections.shuffle(answers);

                //Set the answers in the textview after html escaping (ridding of html symbols)
                ansOne.setText(Jsoup.parse(answers.get(0)).text());
                ansTwo.setText(Jsoup.parse(answers.get(1)).text());
                ansThree.setText(Jsoup.parse(answers.get(2)).text());
                ansFour.setText(Jsoup.parse(answers.get(3)).text());


            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                //Api call failed
                System.out.println("failed");
            }
        });

        //Check if user clicked submit
        btnSubmit = findViewById(R.id.btnAnswer);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    submit = true;
                    //check if user selected correct ans
                    String correctAns = curr.getCorrectAnswer();
                    txtCorrectAns = findViewById(R.id.txtCorrectAns);
                    //Check if user selected the first option
                    if (ansOne.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        //Alert user selected correct answer
                        txtCorrectAns.setText("Correct! Good job");
                        if (ansOne.getText().equals(correctAns)) {
                            //Reset the views to prepare for next question
                            ansOne.setBackgroundResource(R.drawable.correct);
                            //increment counter keeping track of correct answers submitted
                            correct++;
                            //Alert user selected correct answer
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            //Reset the views to prepare for next question
                            incorrect++;
                            ansOne.setBackgroundResource(R.drawable.incorrect);
                            //Alert user selected incorrect answer
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }
                        //Check if user selected the second option
                    } else if (ansTwo.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansTwo.getText().equals(correctAns)) {
                            //Alert user selected correct answer, reset views for next question, increment correct tracker
                            ansTwo.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            //Alert user selected incorrect answer, reset views for next question, increment incorrect tracker
                            incorrect++;
                            ansTwo.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }
                        //Check if user selected the third option
                    } else if (ansThree.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansThree.getText().equals(correctAns)) {
                            //Alert user selected correct answer, reset views for next question, increment correct tracker
                            ansThree.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            //Alert user selected incorrect answer, reset views for next question, increment incorrect tracker
                            incorrect++;
                            ansThree.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }
                        //Check if user selected the fourth option
                    } else if (ansFour.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansFour.getText().equals(correctAns)) {
                            //Alert user selected correct answer, reset views for next question, increment correct tracker
                            ansFour.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            //Alert user selected incorrect answer, reset views for next question, increment incorrect tracker
                            incorrect++;
                            ansFour.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }

                    } else {
                        //User did not select an answer, notify user
                        Toast.makeText(QuizStart.this, "PLease select an answer", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        //Check if user clicked next question
        btnNxt = findViewById(R.id.btnNxt);
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if user first submitted an answer before clicking next
                if (submit == true) {
                    counter++;
                    if (counter < 10) {
                        txtCorrectAns.setText("");
                        submit = false;
                        int num = counter + 1;
                        txtTitle.setText("Question " + num);
                        //get curr question
                        curr = results.get(counter);
                        String questionHtml = curr.getQuestion();
                        String htmlEscape = Jsoup.parse(questionHtml).text();
                        txtQuestion.setText(htmlEscape);

                        //get all the ans to the question
                        answers = curr.getIncorrectAnswers();
                        String correctAnswer = curr.getCorrectAnswer();
                        answers.add(correctAnswer);
                        //shuffle list of ans to randomise where correct ans is
                        Collections.shuffle(answers);

                        //Set the answers based on the question and remove all html symbols
                        ansOne.setText(Jsoup.parse(answers.get(0)).text());
                        ansTwo.setText(Jsoup.parse(answers.get(1)).text());
                        ansThree.setText(Jsoup.parse(answers.get(2)).text());
                        ansFour.setText(Jsoup.parse(answers.get(3)).text());

                        //Reset the radio button views
                        ansOne.setBackgroundResource(0);
                        ansTwo.setBackgroundResource(0);
                        ansThree.setBackgroundResource(0);
                        ansFour.setBackgroundResource(0);

                        //make the buttons clickable
                        ansOne.setEnabled(true);
                        ansTwo.setEnabled(true);
                        ansThree.setEnabled(true);
                        ansFour.setEnabled(true);

                    } else if (counter >= 10){
                        //quiz completed, take user to the results screen
                        Intent intent = new Intent(QuizStart.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", difficulty);
                        intent.putExtra("Incorrect", incorrect);
                        startActivity(intent);
                    }
                } else{
                    //User did not select an answer, notify them
                    Toast.makeText(QuizStart.this, "Please submit an answer", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}