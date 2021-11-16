package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import java.util.ArrayList;

public class TopicQuestion {

    private String category;
    private String question;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private int questionImage;

    public TopicQuestion(String category, String question, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, int questionImage) {
        this.category = category;
        this.question = question;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.questionImage = questionImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public int getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(int questionImage) {
        this.questionImage = questionImage;
    }

    public static ArrayList<TopicQuestion> getQuestions() {
        ArrayList<TopicQuestion> questions = new ArrayList<>();




        return questions;


    }
    }




