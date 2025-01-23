package in.hefshine.quizsystem.service;

import java.util.List;

public class QuizResult {
    private boolean pass;
    private List<QuizQuestionResult> results;

    public QuizResult(boolean pass, List<QuizQuestionResult> results) {
        this.pass = pass;
        this.results = results;
    }

    public boolean isPass() {
        return pass;
    }

    public List<QuizQuestionResult> getResults() {
        return results;
    }

    public static class QuizQuestionResult {
        private String question;
        private String correctAnswer;
        private String userAnswer;
        private boolean correct;

        public QuizQuestionResult(String question, String correctAnswer, String userAnswer, boolean correct) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.userAnswer = userAnswer;
            this.correct = correct;
        }

        public String getQuestion() {
            return question;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public boolean isCorrect() {
            return correct;
        }
    }
    
    
    
}
