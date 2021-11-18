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

public class GameWordActivity extends AppCompatActivity {

    Button btnAnswer;
    Button btnHint;
    TextView txtWord;
    EditText txtAnswer;
    TextView txtClue;
    TextView txtNum;
    TextView txtHint;
    Button btnSkip;

    String answerList[] = {"poseidon", "mercury", "aphrodite", "zeus", "seth", "minerva", "diana", "hades", "cupid", "amon"};
    String answerProperList[] = {"Poseidon", "Mercury", "Aphrodite", "Zeus", "Seth", "Minerva", "Diana", "Hades", "Cupid", "Amon"};
    String clueList[] = {"God of the Sea in Greek Mythology", "Messenger of the Gods in Roman Mythology", "Goddess of Love in Greek Mythology", "King of Gods in Greek Mythology",
            "God of Chaos in Egyptian Mythology", "Goddess of Wisdom in Roman Mythology", "Goddess of the Hunt in Roman Mythology",
            "God of the Underworld in Greek Mythology", "God of Love in Roman Mythology", "God of Air in Egyptian Mythology"};
    String questionList[] = {"O N E D I O P S", "E C R M Y U R", "H O D T E I A P R", "U S E Z", "H E T S", "V A M N E I R", "A A I D N", "D S A E H", "U D I P C", "N O M A"};
    String hintList[] = {"P", "M", "A", "Z", "S","M","D","H","C","A"};

    int counter = 0;
    int correct = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_word);

        txtWord = findViewById(R.id.txtWord);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtClue = findViewById(R.id.txtClue);
        btnAnswer = findViewById(R.id.btnAnswer);
        btnHint = findViewById(R.id.btnHint);
        txtNum = findViewById(R.id.txtNum);
        txtHint = findViewById(R.id.txtHint);
        btnSkip = findViewById(R.id.btnSkip);

        int question = counter + 1;
        txtNum.setText(String.valueOf(question) + "/10");

        txtWord.setText(questionList[counter]);
        txtClue.setText(clueList[counter]);

        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHint.setText("The word starts with the letter " + hintList[counter]);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    txtHint.setText("The answer was " + answerProperList[counter]);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            counter++;
                            if (counter < 10) {
                                txtWord.setText(questionList[counter]);
                                txtClue.setText(clueList[counter]);
                                int question = counter + 1;
                                txtNum.setText(String.valueOf(question) + "/10");
                                txtAnswer.setText("");
                                txtHint.setText("");
                            } else {
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

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = txtAnswer.getText().toString().toLowerCase();

                    if (answer.equals(answerList[counter])) {
                        counter++;
                        if (counter <= 10) {
                            if (counter == 10) {
                                txtAnswer.setBackgroundResource(R.drawable.correct);
                                correct++;
                                txtHint.setText("Correct! You entered the right answer");
                                Intent intent = new Intent(GameWordActivity.this, QuizResult.class);
                                intent.putExtra("Correct", correct);
                                intent.putExtra("Difficulty", "Easy");
                                intent.putExtra("Game", 1);
                                startActivity(intent);

                            }

                            txtAnswer.setBackgroundResource(R.drawable.correct);
                            correct++;
                            txtHint.setText("Correct! You entered the right answer");

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (counter < 10) {
                                        // yourMethod();
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
                            Intent intent = new Intent(GameWordActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 1);
                            startActivity(intent);
                        }

                    } else {
                        txtAnswer.setBackgroundResource(R.drawable.incorrect);
                        txtHint.setText("Incorrect! Click for a hint if you need help");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
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