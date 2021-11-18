package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.TimeUnit;


//Reference for handler: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
public class GameWordActivity extends AppCompatActivity {
    /**This class includes the implementation of the Myth Scramble game**/

    Button btnAnswer;
    Button btnHint;
    TextView txtWord;
    EditText txtAnswer;
    TextView txtClue;
    TextView txtNum;
    TextView txtHint;
    Button btnSkip;

    //List which contains the correct answer in lowercase
    String answerList[] = {"poseidon", "mercury", "aphrodite", "zeus", "seth", "minerva", "diana", "hades", "cupid", "amon"};
    //List which contains the correct answer in Capitalised form
    String answerProperList[] = {"Poseidon", "Mercury", "Aphrodite", "Zeus", "Seth", "Minerva", "Diana", "Hades", "Cupid", "Amon"};
    //List which contains the clue displayed to the user
    String clueList[] = {"God of the Sea in Greek Mythology", "Messenger of the Gods in Roman Mythology", "Goddess of Love in Greek Mythology", "King of Gods in Greek Mythology",
            "God of Chaos in Egyptian Mythology", "Goddess of Wisdom in Roman Mythology", "Goddess of the Hunt in Roman Mythology",
            "God of the Underworld in Greek Mythology", "God of Love in Roman Mythology", "God of Air in Egyptian Mythology"};
    //List which contains the scrambled letters the user has to unscramble
    String questionList[] = {"O N E D I O P S", "E C R M Y U R", "H O D T E I A P R", "U S E Z", "H E T S", "V A M N E I R", "A A I D N", "D S A E H", "U D I P C", "N O M A"};
    //List which contains the first letter of each answer
    String hintList[] = {"P", "M", "A", "Z", "S","M","D","H","C","A"};

    //Counter to keep track of the number of questions
    int counter = 0;
    //Counter to keep track of the number of correct answers submitted
    int correct = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_word);

        //Initialise the textviews a
        txtWord = findViewById(R.id.txtWord);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtClue = findViewById(R.id.txtClue);
        btnAnswer = findViewById(R.id.btnAnswer);
        btnHint = findViewById(R.id.btnHint);
        txtNum = findViewById(R.id.txtNum);
        txtHint = findViewById(R.id.txtHint);
        btnSkip = findViewById(R.id.btnSkip);

        //Display the current question number
        int question = counter + 1;
        txtNum.setText(String.valueOf(question) + "/10");

        //Display the question and the clue prompt to the user
        txtWord.setText(questionList[counter]);
        txtClue.setText(clueList[counter]);

        //Check if user pressed for a hint
        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display the first letter of the answer as a hint to the user
                txtHint.setText("The word starts with the letter " + hintList[counter]);
            }
        });

        //Check if user pressed to skip the question
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display the correct answer
                txtHint.setText("The answer was " + answerProperList[counter]);

                //Reference: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
                //Delay 1 second before moving on to next question
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //Increase the counter to keep track of the question we are now on
                        counter++;
                        //Check if there are still questions left
                        if (counter < 10) {
                            //Display the required information for the next question
                            txtWord.setText(questionList[counter]);
                            txtClue.setText(clueList[counter]);
                            int question = counter + 1;
                            txtNum.setText(String.valueOf(question) + "/10");
                            txtAnswer.setText("");
                            txtHint.setText("");
                        } else {
                            //No more questions, skip to the results page
                            Intent intent = new Intent(GameWordActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 1);
                            startActivity(intent);
                        }

                    }
                }, 1000);
            }

        });

        //Check if user submitted an answer
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Get the user answer from the edit text
            String answer = txtAnswer.getText().toString().toLowerCase();

                //Check if the inputted answer matches the actual correct answer
                if (answer.equals(answerList[counter])) {
                    //Increment the counter to keep track of question we are now on
                    counter++;
                    //Check if there are still questions left
                    if (counter <= 10) {
                        //Check if we are on the last question
                        if (counter == 10) {
                            //Notify user selected the correct answer
                            txtAnswer.setBackgroundResource(R.drawable.correct);
                            //Increment count of number of correct questions
                            correct++;
                            txtHint.setText("Correct! You entered the right answer");
                            //Since we are on last question, now go to the results page
                            Intent intent = new Intent(GameWordActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 1);
                            startActivity(intent);

                        }
                        //Notify user has selected the correct answer
                        txtAnswer.setBackgroundResource(R.drawable.correct);
                        correct++;
                        txtHint.setText("Correct! You entered the right answer");

                        //Reference: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
                        //Delay 1 second before moving on to next question
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (counter < 10) {
                                    //Display the required information for the next question
                                    txtAnswer.setBackgroundResource(0);
                                    txtAnswer.setText("");
                                    txtWord.setText(questionList[counter]);
                                    txtClue.setText(clueList[counter]);
                                    int question = counter + 1;
                                    txtNum.setText(String.valueOf(question) + "/10");
                                    txtHint.setText("");
                                }

                            }
                        }, 1000);

                    } else {
                        //No more questions left, move to the results page
                        Intent intent = new Intent(GameWordActivity.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", "Easy");
                        intent.putExtra("Game", 1);
                        startActivity(intent);
                    }

                } else {
                    //User has inputted an incorrect answer
                    //Notify the user that they have inputted wrong answer
                    txtAnswer.setBackgroundResource(R.drawable.incorrect);
                    //Prompt the user to press the button for a hint
                    txtHint.setText("Incorrect! Click for a hint if you need help");
                    //Reference: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
                    //Delay 1 second before resetting the information to the current question
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Reset the textviews to display the current question again
                            txtAnswer.setBackgroundResource(0);
                            txtAnswer.setText("");
                            txtHint.setText("");
                        }
                    }, 1000);
                }
            }
        });
    }

}