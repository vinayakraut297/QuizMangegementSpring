package in.hefshine.quizsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hefshine.quizsystem.entity.Question;
import in.hefshine.quizsystem.repository.QuestionRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> getQuestionsByQuizId(Long quizId) {
		return questionRepository.findByQuizId(quizId);
	}

	public List<Question> getQuestionsByQuizTitle(String title) {
		return questionRepository.findByQuizTitle(title);
	}

	public void addQuestion(Question question) {
		if (question.getQuiz() == null) {
			throw new IllegalArgumentException("Quiz must be associated with the question.");
		}
		question.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		questionRepository.save(question);
	}

	public QuizResult evaluateQuiz(List<Integer> userAnswers, String quizTitle) {
		List<Question> questions = getQuestionsByQuizTitle(quizTitle);
		List<QuizResult.QuizQuestionResult> results = new ArrayList<>();
		boolean pass = true;

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			int correctAnswerIndex = question.getCorrectAnswerIndex();
			boolean isCorrect = userAnswers.get(i) == correctAnswerIndex;
			if (!isCorrect)
				pass = false;
			results.add(
					new QuizResult.QuizQuestionResult(question.getText(), question.getOptions().get(correctAnswerIndex),
							question.getOptions().get(userAnswers.get(i)), isCorrect));
		}

		return new QuizResult(pass, results);
	}
}
