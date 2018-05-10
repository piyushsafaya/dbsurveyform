package municipality.dubai.com.welcometodubaimunicipalityevent.Models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by grigorz on 9/18/17.
 */

public class PredefinedQuestions {
    private ArrayList<Question> questions;
    private int[] answersPositions;
    private int correctAnswersCount;

    public PredefinedQuestions () {
        questions = new ArrayList<>();
        answersPositions = new int[5];
        correctAnswersCount = 0;


        Question newQuestion = new Question();



        newQuestion.setQuestion("1. Which reserve is the first unfenced reserve open to public and is covering around 9% of the total land area of Dubai?",
                new String[]{
                        "Al Marmoum Desert Reserve",
                        "Ras Al Khor Wildlife Sanctuary",
                        "Jabel Ali Marine Marine Sanctuary",
                        "Hatta Mountain Reserve"},
                0
        );
        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("2. This Wildlife Sanctuary covers the coastline between Jebel Ali and Ghantoot. Which sanctuary is it?",
                new String[] {
                        "Ras Al Khor Wildlife Sanctuary",
                        "Al Marmoum Desert Reserve",
                        "Al Ghaf Reserve",
                        "Jabel Ali Marine Sanctuary"
                }, 3);
        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("3. One of the most popular local fishes in UAE and is endangered, what is it?",
                new String[] {
                        "Hammour",
                        "Tuna Fish",
                        "Trout sweetlips (Farsh Fish)",
                        "Seabream Fish"
                }, 0);
        questions.add(newQuestion);


        newQuestion = new Question();
        newQuestion.setQuestion("4. A native to the desert, this animal has 2 horns and is white in color with brown legs and black strips where the head and neck meets.",
                new String[]{
                        "Hawksbill Turtle",
                        "Fennec Fox",
                        "Arabian Oryx",
                        "Osprey"
                }, 2);
        questions.add(newQuestion);


        newQuestion = new Question();
        newQuestion.setQuestion("5. One of the common large eagles in the UAE. It migrates to the UAE during the winter season, their habitat is destroyed due to urbanization:",
                new String[]{
                        "Greater Flamingo",
                        "Greater Spotted Eagle",
                        "Houbara Bustard",
                        "Black tailed godwit"
                }, 1);
        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("6. I walk in the desert and can have one hump or two. What am I?",
                new String[]{
                        "Lizard",
                        "Camel",
                        "Snake",
                        "Arabian leopard"
                }, 1);

        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("7. What is the UAE’s national animal?",
                new String[]{
                        "Arabian Oryx",
                        "Eagle",
                        "Camel",
                        "Fox"
                }, 0);
        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("8. How many lungs do snakes have?",
                new String[] {
                        "One",
                        "Two",
                        "Three",
                        "Six"
                }, 0);
        questions.add(newQuestion);


        newQuestion = new Question();
        newQuestion.setQuestion("9. What is a group of lions called?",
                new String[] {
                        "A pod",
                        "A pride",
                        "A flock",
                        "A herd"
                }, 1);
        questions.add(newQuestion);

        newQuestion = new Question();
        newQuestion.setQuestion("10. I swim in the sea, I like to eat jellyfish and I have a hard shell. What am I?",
                new String[]{
                        "Sea Turtle",
                        "Polar Bear",
                        "Fish",
                        "Flamingo"
                }, 0 );
        questions.add(newQuestion);

    }

    public int getSize() {
        return questions.size();
    }

    public int setAnswer (int questionPosition, int answerPosition){
        int correctAnswer = questions.get(questionPosition).getRightAnswerPosition();
        questions.get(questionPosition).setAnsweredPosition(answerPosition);
        if (correctAnswer == answerPosition) {
            correctAnswersCount++;
            return answerPosition;
        } else {
            return correctAnswer;
        }
    }

    public Question getCurrentQuestion(int position) {
        return questions.get(position);
    }


}
