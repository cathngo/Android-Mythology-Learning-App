package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePictureActivity extends AppCompatActivity {
    int leftList[] = {R.drawable.centaur, R.drawable.fillertwo, R.drawable.fillerthree, R.drawable.minotaur, R.drawable.sphinx};
    int rightList[] = {R.drawable.fillerone,R.drawable.griffin, R.drawable.hydra,  R.drawable.fillerfour, R.drawable.fillerfive};
    int correctList[] = {0,1,1,0,0};
    String questionList[] = {"Select the image of a Centaur","Select the image of a Griffin", "Select the image of a Hydra", "Select the image of a Minotaur", "Select the image of a Sphinx" };

    TextView txtNumQ;
    TextView txtNotify;
    ImageView imgLeft;
    ImageView imgRight;
    TextView txtMonsterQ;

    int counter = 0;
    int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_picture);

        txtNumQ = findViewById(R.id.txtNumQ);
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        txtNotify = findViewById(R.id.txtNotify);
        txtMonsterQ = findViewById(R.id.txtMonsterQ);

        int question = counter + 1;
        txtNumQ.setText(String.valueOf(question) + "/5");

        imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
        imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));

        txtMonsterQ.setText(questionList[counter]);

        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (correctList[counter] == 0) {
                    counter++;
                    //correct
                    if (counter <= 5) {
                        if (counter == 5) {
                            txtNotify.setText("Correct choice!");
                            correct++;
                            imgLeft.setBackgroundResource(R.drawable.correct);
                            //end
                            Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 2);
                            startActivity(intent);
                        }
                        txtNotify.setText("Correct choice!");
                        correct++;
                        imgLeft.setBackgroundResource(R.drawable.correct);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (counter < 5) {
                                    int question = counter + 1;
                                    txtNumQ.setText(String.valueOf(question) + "/5");
                                    imgLeft.setBackgroundResource(0);
                                    txtNotify.setText("");
                                    txtMonsterQ.setText(questionList[counter]);
                                    imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                                    imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                                }

                            }
                        }, 1000);
                    } else {
                        //end
                        Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", "Easy");
                        intent.putExtra("Game", 2);
                        startActivity(intent);
                    }

                } else {
                    //incorrect
                    counter++;
                    if (counter <= 5) {
                        if (counter == 5) {
                            txtNotify.setText("incorrect choice!");
                            imgLeft.setBackgroundResource(R.drawable.incorrect);
                            //end
                            Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 2);
                            startActivity(intent);
                        }
                        txtNotify.setText("incorrect choice!");
                        imgLeft.setBackgroundResource(R.drawable.incorrect);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (counter < 5) {
                                    int question = counter + 1;
                                    txtNumQ.setText(String.valueOf(question) + "/5");
                                    imgLeft.setBackgroundResource(0);
                                    txtNotify.setText("");
                                    txtMonsterQ.setText(questionList[counter]);
                                    imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                                    imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                                }

                            }
                        }, 1000);

                    } else {
                        //end
                        Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", "Easy");
                        intent.putExtra("Game", 2);
                        startActivity(intent);
                    }
                }
            }

        });

        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (correctList[counter] == 1) {
                    counter++;
                    //correct
                    if (counter <= 5) {
                        if (counter == 5) {
                            //last page
                            txtNotify.setText("Correct choice!");
                            correct++;
                            imgRight.setBackgroundResource(R.drawable.correct);
                            //end
                            Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 2);
                            startActivity(intent);
                        }
                        txtNotify.setText("Correct choice!");
                        correct++;
                        imgRight.setBackgroundResource(R.drawable.correct);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (counter < 5) {
                                    int question = counter + 1;
                                    txtNumQ.setText(String.valueOf(question) + "/5");
                                    imgRight.setBackgroundResource(0);
                                    txtNotify.setText("");
                                    txtMonsterQ.setText(questionList[counter]);
                                    imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                                    imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                                }

                            }
                        }, 1000);
                    } else {
                        //end
                        Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", "Easy");
                        intent.putExtra("Game", 2);
                        startActivity(intent);
                    }

                } else {
                    //incorrect
                    counter++;
                    if (counter <= 5) {
                        if (counter == 5) {
                            txtNotify.setText("incorrect choice!");
                            imgRight.setBackgroundResource(R.drawable.incorrect);
                            //end
                            Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Difficulty", "Easy");
                            intent.putExtra("Game", 2);
                            startActivity(intent);

                        }
                        txtNotify.setText("incorrect choice!");
                        imgRight.setBackgroundResource(R.drawable.incorrect);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (counter < 5) {
                                    int question = counter + 1;
                                    txtNumQ.setText(String.valueOf(question) + "/5");
                                    imgRight.setBackgroundResource(0);
                                    txtNotify.setText("");
                                    txtMonsterQ.setText(questionList[counter]);
                                    imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                                    imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                                }
                            }
                        }, 1000);

                    } else {
                        //end
                        Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                        intent.putExtra("Correct", correct);
                        intent.putExtra("Difficulty", "Easy");
                        intent.putExtra("Game", 2);
                        startActivity(intent);
                    }
                }
            }

        });
    }
}