package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//Reference for handler: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
public class GamePictureActivity extends AppCompatActivity {
    /**This class includes the implementation of the Monster Match game**/

    //Lists containing the images for the user to select
    int leftList[] = {R.drawable.centaur, R.drawable.fillertwo, R.drawable.fillerthree, R.drawable.minotaur, R.drawable.sphinx};
    int rightList[] = {R.drawable.fillerone,R.drawable.griffin, R.drawable.hydra,  R.drawable.fillerfour, R.drawable.fillerfive};

    //List containing the correct answer. 0 represents the left image is correct. 1 represents the right image is correct.
    int correctList[] = {0,1,1,0,0};

    //List containing the question to set
    String questionList[] = {"Select the image of a Centaur","Select the image of a Griffin", "Select the image of a Hydra", "Select the image of a Minotaur", "Select the image of a Sphinx" };

    TextView txtNumQ;
    TextView txtNotify;
    ImageView imgLeft;
    ImageView imgRight;
    TextView txtMonsterQ;

    //Counters to keep track of the question and number of correct answers
    int counter = 0;
    int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_picture);

        //Initialise the textviews and images
        txtNumQ = findViewById(R.id.txtNumQ);
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        txtNotify = findViewById(R.id.txtNotify);
        txtMonsterQ = findViewById(R.id.txtMonsterQ);

        //Display the current question number
        int question = counter + 1;
        txtNumQ.setText(String.valueOf(question) + "/5");

        //Set the first images
        imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
        imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));

        //Set the first question
        txtMonsterQ.setText(questionList[counter]);

        //Check if user selected the left image
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfImageSelected(imgLeft,0);
            }
        });

        //Implement the same on click listener used on the left image for the right image.
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfImageSelected(imgRight,1);
            }
        });
    }

    //Check if the user selected the left of right image, notifies if the image selected
    //was correct and then moves on to the next question.
    public void checkIfImageSelected(ImageView image, int correct) {
        //Check if the correct answer is the left image
        if (correctList[counter] == correct) {
            //Increase the counter to skip to the next question
            counter++;
            //Check if there are still questions left
            if (counter <= 5) {
                //Check if this is the last question
                if (counter == 5) {
                    //Notify the user has selected the correct answer
                    txtNotify.setText("Correct choice!");
                    image.setBackgroundResource(R.drawable.correct);
                    //Increment the number of correct answers selected
                    correct++;
                    //Since this is the last quesiton, skip to the results page
                    Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                    intent.putExtra("Correct", correct);
                    intent.putExtra("Difficulty", "Easy");
                    intent.putExtra("Game", 2);
                    startActivity(intent);
                }
                //Notify user has selected the correct answer
                txtNotify.setText("Correct choice!");
                image.setBackgroundResource(R.drawable.correct);
                //Increment the number of correct answers selected
                correct++;

                //Delay 1 second before skipping over to the next question
                //Reference: https://stackoverflow.com/questions/41664409/wait-for-5-seconds
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (counter < 5) {
                            //Set the required information for the next question
                            int question = counter + 1;
                            txtNumQ.setText(String.valueOf(question) + "/5");
                            image.setBackgroundResource(0);
                            txtNotify.setText("");
                            txtMonsterQ.setText(questionList[counter]);
                            imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                            imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                        }

                    }
                }, 1000);
            } else {
                //No more questions left, skip to the result page
                Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                intent.putExtra("Correct", correct);
                intent.putExtra("Difficulty", "Easy");
                intent.putExtra("Game", 2);
                startActivity(intent);
            }

        } else {
            //User has selected the incorrect answer
            counter++;
            if (counter <= 5) {
                if (counter == 5) {
                    //Notify user has selected the incorrect answer
                    txtNotify.setText("Incorrect choice!");
                    image.setBackgroundResource(R.drawable.incorrect);
                    //Switch the the results page as this is the last question
                    Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                    intent.putExtra("Correct", correct);
                    intent.putExtra("Difficulty", "Easy");
                    intent.putExtra("Game", 2);
                    startActivity(intent);
                }
                //Otherwise, not the last question
                //Notify user has selected the incorect answer
                txtNotify.setText("Incorrect choice!");
                image.setBackgroundResource(R.drawable.incorrect);

                //Delay 1 second before skipping to the next question
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (counter < 5) {
                            //Display the required information for the next question
                            int question = counter + 1;
                            txtNumQ.setText(String.valueOf(question) + "/5");
                            image.setBackgroundResource(0);
                            txtNotify.setText("");
                            txtMonsterQ.setText(questionList[counter]);
                            imgLeft.setImageDrawable(getResources().getDrawable(leftList[counter]));
                            imgRight.setImageDrawable(getResources().getDrawable(rightList[counter]));
                        }

                    }
                }, 1000);

            } else {
                //No more questions left, skip to the result page
                Intent intent = new Intent(GamePictureActivity.this, QuizResult.class);
                intent.putExtra("Correct", correct);
                intent.putExtra("Difficulty", "Easy");
                intent.putExtra("Game", 2);
                startActivity(intent);
            }
        }
    }
}