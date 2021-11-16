package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

    List<String> answers;
    List<Result> results;
    Result curr;
    int correct;
    int incorrect;

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

                //set textviews
                txtQuestion = findViewById(R.id.txtQuestion);
                ansOne = findViewById(R.id.ansOne);
                ansTwo = findViewById(R.id.ansTwo);
                ansThree = findViewById(R.id.ansThree);
                ansFour = findViewById(R.id.ansFour);

                //get curr question
                curr = results.get(counter);
                txtQuestion.setText(curr.getQuestion());

                //get all the ans to the question
                answers =  curr.getIncorrectAnswers();
                String correctAns = curr.getCorrectAnswer();
                answers.add(correctAns);
                //shuffle list of ans to randomise where correct ans is
                Collections.shuffle(answers);

                ansOne.setText(answers.get(0));
                ansTwo.setText(answers.get(1));
                ansThree.setText(answers.get(2));
                ansFour.setText(answers.get(3));


            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                System.out.println("failed");
            }
        });

        btnNxt = findViewById(R.id.btnNxt);
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if user selected correct ans
                String correctAns = curr.getCorrectAnswer();

                boolean selected = false;
                if (ansOne.isChecked()) {
                    selected = true;
                    if (ansOne.getText().equals(correctAns)) {
                        System.out.println("1 correct ans selected");
                        correct++;
                    } else {
                        incorrect++;
                        System.out.println(" 1 incorrect ans selected");
                    }

                } else if (ansTwo.isChecked()) {
                    selected = true;
                    if (ansTwo.getText().equals(correctAns)) {
                        System.out.println("2 correct ans selected");
                        correct++;
                    } else {
                        incorrect++;
                        System.out.println(" 2 incorrect ans selected");
                    }

                } else if (ansThree.isChecked()) {
                    selected = true;
                    if (ansThree.getText().equals(correctAns)) {
                        System.out.println("3 correct ans selected");
                        correct++;
                    } else {
                        incorrect++;
                        System.out.println(" 3 incorrect ans selected");
                    }

                } else if (ansFour.isChecked()) {
                    selected = true;
                    if (ansFour.getText().equals(correctAns)) {
                        System.out.println("4 correct ans selected");
                        correct++;
                    } else {
                        incorrect++;
                        System.out.println(" 4 incorrect ans selected");
                    }

                }

                counter++;

                if (counter < 10 && selected == true) {
                    //get curr question
                    curr = results.get(counter);
                    txtQuestion.setText(curr.getQuestion());

                    //get all the ans to the question
                    answers = curr.getIncorrectAnswers();
                    String correctAnswer = curr.getCorrectAnswer();
                    answers.add(correctAnswer);
                    //shuffle list of ans to randomise where correct ans is
                    Collections.shuffle(answers);

                    ansOne.setText(answers.get(0));
                    ansTwo.setText(answers.get(1));
                    ansThree.setText(answers.get(2));
                    ansFour.setText(answers.get(3));

                } else if (counter >= 10 && selected == true){
                    //quiz completed
                    Intent intent = new Intent(QuizStart.this, QuizResult.class);
                    intent.putExtra("Correct", correct);
                    intent.putExtra("Incorrect", incorrect);
                    startActivity(intent);
                } else if (selected == false) {
                    Toast.makeText(QuizStart.this, "PLease select an answer", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}