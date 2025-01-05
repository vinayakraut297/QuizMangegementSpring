package in.hefshine.quizsystem.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hefshine.quizsystem.entity.Answer;
import in.hefshine.quizsystem.entity.Question;
import in.hefshine.quizsystem.entity.Quiz;
import in.hefshine.quizsystem.entity.QuizSubmission;
import in.hefshine.quizsystem.exception.ResourceNotFoundException;
import in.hefshine.quizsystem.repository.AnswerRepository;
import in.hefshine.quizsystem.repository.QuestionRepository;
import in.hefshine.quizsystem.repository.QuizRepository;

@Service
public class QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	AnswerRepository answerRepository;

	public void createQuiz(Quiz quiz) {
		quiz.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		quizRepository.save(quiz);
	}

	public List<Quiz> getAllQuizzes() {
		return quizRepository.findAll();
	}

	public Quiz getQuizById(Long id) throws ResourceNotFoundException {
		Quiz quiz = quizRepository.findByIdWithQuestions(id);
		if (quiz == null) {
			throw new ResourceNotFoundException("Quiz not found with id: " + id);
		}
		return quiz;
	}
	
	@Autowired
	private QuestionRepository questionRepository;
	public void addQuiz(Question question) {
		if (question.getQuiz() == null || question.getQuiz().getId() == 0) {
			throw new IllegalArgumentException("Quiz must be associated with the question.");
		}
		question.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		questionRepository.save(question);
	}
	
	public void saveSubmission(QuizSubmission submission) {
	    String quizTitle = submission.getQuizTitle();
	    Map<Long, Integer> answers = submission.getAnswers();
	    Quiz quiz = quizRepository.findByTitle(quizTitle);

	    if (quiz != null) {
	        answers.forEach((questionId, selectedOptionIndex) -> {
	            Question question = questionRepository.findById(questionId).orElse(null);
	            if (question != null) {
	                Answer answer = new Answer();
	                answer.setQuestion(question);
	                answer.setSelectedOptionIndex(selectedOptionIndex);
	                answer.setQuiz(quiz);
	                answerRepository.save(answer);
	            }
	        });
	    }
	}

	
	

}
