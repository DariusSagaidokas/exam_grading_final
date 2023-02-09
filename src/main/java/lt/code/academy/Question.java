package lt.code.academy;

import java.util.List;
public class Question {
    private int questionNumber;
    private String question;
    private List<String> answers;
    private String correctAnswer;

    public Question(int questionNumber, String question, List<String> answers, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    public Question(){}

    public String getQuestion() {
        return question;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}