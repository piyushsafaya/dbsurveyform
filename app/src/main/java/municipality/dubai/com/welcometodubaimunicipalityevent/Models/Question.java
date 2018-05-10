package municipality.dubai.com.welcometodubaimunicipalityevent.Models;

/**
 * Created by grigorz on 9/18/17.
 */

public class Question {
    private String question;
    private String[] possibleAnswers;
    private int rightAnswerPosition;
    private int answeredPosition;

    public Question() {

    }

    public void setQuestion (String question, String[] possibleAnswers, int rightAnswerPosition) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.rightAnswerPosition = rightAnswerPosition;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public int getRightAnswerPosition() {
        return rightAnswerPosition;
    }

    public int getAnsweredPosition() {
        return answeredPosition;
    }

    public void setAnsweredPosition(int answeredPosition) {
        this.answeredPosition = answeredPosition;
    }

    public int getAnswersCount () {
        return possibleAnswers.length;
    }
}
