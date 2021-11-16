package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {

    @GET("api.php?amount=10&category=20&type=multiple")
    Call<Question> getQuestions();

    @GET("api.php?amount=10&category=20&difficulty=easy&type=multiple")
    Call<Question> getEasyQuestions();

    @GET("api.php?amount=10&category=20&difficulty=medium&type=multiple")
    Call<Question> getMediumQuestions();

    @GET("api.php?amount=10&category=20&difficulty=hard&type=multiple")
    Call<Question> getHardQuestions();
}
