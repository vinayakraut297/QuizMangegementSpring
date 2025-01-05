package in.hefshine.quizsystem.entity;

import java.util.Map;

public class QuizSubmission {
    private String quizTitle;
    private Map<Long, Integer> answers;

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public Map<Long, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, Integer> answers) {
        this.answers = answers;
    }
}
