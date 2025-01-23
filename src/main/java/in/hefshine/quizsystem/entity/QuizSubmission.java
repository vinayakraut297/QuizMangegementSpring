package in.hefshine.quizsystem.entity;

import java.util.Map;
//
//public class QuizSubmission {
//    private String quizTitle;
//    private Map<Long, Integer> answers;
//
//    public String getQuizTitle() {
//        return quizTitle;
//    }
//
//    public void setQuizTitle(String quizTitle) {
//        this.quizTitle = quizTitle;
//    }
//
//    public Map<Long, Integer> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(Map<Long, Integer> answers) {
//        this.answers = answers;
//    }
//}


public class QuizSubmission {
    private String quizTitle;
    private Map<Long, Integer> answers; // Key: question ID, Value: selected option ID

    // Getters and setters
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

