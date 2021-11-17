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

public class QuizStart extends AppCompatActivity {
    int counter = 0;
    TextView txtQuestion;
    RadioButton ansOne;
    RadioButton ansTwo;
    RadioButton ansThree;
    RadioButton ansFour;
    Button btnNxt;
    TextView txtTitle;
    TextView txtChoose;

    List<String> answers;
    List<Result> results;
    Result curr;
    int correct;
    int incorrect;
    Button btnSubmit;
    boolean selected = false;
    boolean submit = false;
    TextView txtCorrectAns;

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

        //check what difficulty user selected
        Intent incomingIntent = getIntent();
        String difficulty = incomingIntent.getStringExtra("Difficulty");
        Call<Question> responseCall;
        if (difficulty.equals("Easy")) {
            responseCall = service.getEasyQuestions();
        } else if (difficulty.equals("Medium")) {
            responseCall = service.getMediumQuestions();
        } else {
            responseCall = service.getHardQuestions();
        }



        responseCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Question question = response.body();
                results = question.getResults();
                for (Result result: results) {
                    System.out.println(result.getQuestion());
                    System.out.println(result.getDifficulty());
                }

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
                String questionHtml = curr.getQuestion();
                String htmlEscape = Jsoup.parse(questionHtml).text();
                txtQuestion.setText(htmlEscape);

                //get all the ans to the question
                answers =  curr.getIncorrectAnswers();
                String correctAns = curr.getCorrectAnswer();
                answers.add(correctAns);
                //shuffle list of ans to randomise where correct ans is
                Collections.shuffle(answers);

                ansOne.setText(Jsoup.parse(answers.get(0)).text());
                ansTwo.setText(Jsoup.parse(answers.get(1)).text());
                ansThree.setText(Jsoup.parse(answers.get(2)).text());
                ansFour.setText(Jsoup.parse(answers.get(3)).text());


            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                System.out.println("failed");
            }
        });

        btnSubmit = findViewById(R.id.btnAnswer);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    submit = true;
                    //check if user selected correct ans
                    String correctAns = curr.getCorrectAnswer();
                    txtCorrectAns = findViewById(R.id.txtCorrectAns);
                    if (ansOne.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        txtCorrectAns.setText("Correct! Good job");
                        if (ansOne.getText().equals(correctAns)) {
                            System.out.println("1 correct ans selected");
                            ansOne.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            incorrect++;
                            System.out.println(" 1 incorrect ans selected");
                            ansOne.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }

                    } else if (ansTwo.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansTwo.getText().equals(correctAns)) {
                            System.out.println("2 correct ans selected");
                            ansTwo.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            incorrect++;
                            System.out.println(" 2 incorrect ans selected");
                            ansTwo.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }

                    } else if (ansThree.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansThree.getText().equals(correctAns)) {
                            System.out.println("3 correct ans selected");
                            ansThree.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            incorrect++;
                            System.out.println(" 3 incorrect ans selected");
                            ansThree.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }

                    } else if (ansFour.isChecked()) {
                        //make the buttons no longer clickable
                        ansOne.setEnabled(false);
                        ansTwo.setEnabled(false);
                        ansThree.setEnabled(false);
                        ansFour.setEnabled(false);
                        selected = true;
                        if (ansFour.getText().equals(correctAns)) {
                            System.out.println("4 correct ans selected");
                            ansFour.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtCorrectAns.setText("Correct! You selected the right answer");
                        } else {
                            incorrect++;
                            System.out.println(" 4 incorrect ans selected");
                            ansFour.setBackgroundResource(R.drawable.incorrect);
                            txtCorrectAns.setText("Incorrect! The correct answer is " + (Jsoup.parse(correctAns).text()));
                        }

                    } else {
                        Toast.makeText(QuizStart.this, "PLease select an answer", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });


        btnNxt = findViewById(R.id.btnNxt);
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                        ansOne.setText(Jsoup.parse(answers.get(0)).text());
                        ansTwo.setText(Jsoup.parse(answers.get(1)).text());
                        ansThree.setText(Jsoup.parse(answers.get(2)).text());
                        ansFour.setText(Jsoup.parse(answers.get(3)).text());


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
                        //quiz completed
                        Intent intent = new Intent(QuizStart.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", difficulty);
                        intent.putExtra("Incorrect", incorrect);
                        startActivity(intent);
                    }
                } else{
                    Toast.makeText(QuizStart.this, "Please submit an answer", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}