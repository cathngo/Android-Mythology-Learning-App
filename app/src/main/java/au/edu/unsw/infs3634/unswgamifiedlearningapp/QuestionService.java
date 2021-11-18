package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {
    /**This interface is to create get requests through the OpenTrivia API**/

    //Get easy difficulty questions
    @GET("api.php?amount=10&category=20&difficulty=easy&type=multiple")
    Call<Question> getEasyQuestions();

    //Get medium difficulty questions
    @GET("api.php?amount=10&category=20&difficulty=medium&type=multiple")
    Call<Question> getMediumQuestions();

    //Get hard difficulty questions
    @GET("api.php?amount=10&category=20&difficulty=hard&type=multiple")
    Call<Question> getHardQuestions();
}
