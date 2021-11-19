package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import java.util.ArrayList;
import java.util.List;

public class TopicQuestion {


    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String userSelectedAnswer;


    //constructor for the TopicQuestion class
    public TopicQuestion(String userSelectedAnswer, String question, String option1, String option2, String option3, String option4, String answer) {

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.userSelectedAnswer = userSelectedAnswer;
    }


    //getters and setters
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }



    //Add questions for each topic

    public static ArrayList<TopicQuestion> getGreekQuestions() {
        ArrayList<TopicQuestion> questions = new ArrayList<>();


        questions.add(new TopicQuestion("", "Who is the god of kings and princes?", "Zeus", "Thor", "Apollo", "Dike", "Zeus"));
        questions.add(new TopicQuestion("", "What were the gods of feats and banquets?", "The Theoi Olympioi", "The Theoi Daitioi", "The Theoi Georgikoi", "The Theoi Iatrikoi", "The Theoi Daitioi"));
        questions.add(new TopicQuestion("", "Who was the leader of the gods of agriculture?", "Zeus", "Hestia", "Demetor", "Poseidon", "Demetor"));

        return questions;


    }

    public static ArrayList<TopicQuestion> getRomanQuestions() {
        ArrayList<TopicQuestion> questions = new ArrayList<>();


        questions.add(new TopicQuestion("", "Which culture significantly influenced Roman mythology?", "British", "Italian", "Egyptian", "Greek", "Greek"));
        questions.add(new TopicQuestion("", "Who were the main god and goddesses in Roman culture?", "Jupiter, Zeus, Minerva", "Jupiter, Juno, Minerva", "Zeus, Apollo, Demetor", "Juno, Minerva, Mars", "Jupiter, Juno, Minerva"));
        questions.add(new TopicQuestion("", "Which Greek god is Jupiter thought to have originated from?", "Zeus", "Apollo", "Hades", "Artemis", "Zeus"));
        questions.add(new TopicQuestion("", "Which Greek god inspired Neptune?", "Zeus", "Poseidon", "Hades", "Artemis", "Poseidon"));
        questions.add(new TopicQuestion("", "What type of god is Neptune?", "Underworld", "War", "Sea", "Marketplace", "Sea"));
        questions.add(new TopicQuestion("", "Which Roman god does not have Greek origins?", "Janus", "Diana", "Pluto", "Jupiter", "Janus"));
        questions.add(new TopicQuestion("", "Who was Janus' son?", "John", "Diana", "Mars", "Tiberinus", "Tiberinus"));
        questions.add(new TopicQuestion("", "Who were the parents of Romulus and Remus?", "Jupiter and Juno", "Mars and Rhea", "Jupiter and Minerva", "Artemis and Ashley", "Mars and Rhea"));
        return questions;


    }

    public static ArrayList<TopicQuestion> getEgyptianQuestions() {
        ArrayList<TopicQuestion> questions = new ArrayList<>();


        questions.add(new TopicQuestion("", "Over the course of history, how many gods and goddesses were worshipped in Egypt?", "A few", "Hundreds", "Thousands", "11", "Hundreds"));
        questions.add(new TopicQuestion("", "Who was god of the underworld?", "Osiris", "Anubis", "Horus", "Thoth", "Osiris"));
        questions.add(new TopicQuestion("", "Which of these did Osiris represent?", "The cycle of Nile floods", "Baboons", "The pyramids", "Hunger", "The cycle of Nile floods"));
        questions.add(new TopicQuestion("", "Who was Osiris' wife?", "Horus", "Nephthys", "Isis", "Aphrodite", "Isis"));
        questions.add(new TopicQuestion("", "Who was Osiris and Isis' child?", "Anubis", "Thoth", "Hathor", "Horus", "Horus"));
        questions.add(new TopicQuestion("", "How was Hathor normally depicted?", "A hawk", "A baboon", "A cow", "A rabbit", "A cow"));
        questions.add(new TopicQuestion("", "Which of these did Hathor embody?", "Meat", "Motherhood", "Language", "Hunger", "Motherhood"));
        questions.add(new TopicQuestion("", "What was Thoth said to have created?", "The hieroglyphic script", "The pyramids", "The moon", "The sun", "The hieroglyphic script"));

        return questions;


    }
    public static ArrayList<TopicQuestion> getOtherQuestions() {
        ArrayList<TopicQuestion> questions = new ArrayList<>();


        questions.add(new TopicQuestion("", "What are the two divisions of Norse gods?", "Asir and Vanir", "Thor and Odin", "Sun and Moon", "War and Peace", "Asir and Vanir"));
        questions.add(new TopicQuestion("", "The Asir are primarily gods of what?", "Marketplace", "Agriculture", "Peace", "War", "War"));
        questions.add(new TopicQuestion("", "Who is the trickster god?", "Thor", "Frigg", "Loki", "Tyr", "Loki"));
        questions.add(new TopicQuestion("", "What is Thor's hammer called?", "Mjölnir", "Jötnar", "Tyr", "Jörmungandr", "Mjölnir"));
        questions.add(new TopicQuestion("", "What is Jörmungandr?", "A giant", "A serpent", "A giant serpent", "A god", "A serpent"));
        questions.add(new TopicQuestion("", "What does the Japanese word 'Kami' mean?", "Gods", "Religion", "Language", "Mythology", "Gods"));
        questions.add(new TopicQuestion("", "What does Polynesian mythology place get emphasis on?", "The sea", "War", "Nature", "Fun", "Nature"));
        questions.add(new TopicQuestion("", "Who is the most popular Polynesian character in mythology?", "Moana", "Maui", "The pacific ocean", "Apollo", "Maui"));

        return questions;


    }


    //create a method that takes in the category desired and returns the matching List of questions
    public static List<TopicQuestion> getQuestions(String category){

        switch (category) {
            case "Greek":
                return getGreekQuestions();
            case "Roman":
                return getRomanQuestions();
            case "Egyptian":
                return getEgyptianQuestions();
            default:
                return getOtherQuestions();

        }

    }



    }




