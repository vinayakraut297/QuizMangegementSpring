package in.hefshine.quizsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.quizsystem.entity.Question;
import in.hefshine.quizsystem.entity.Quiz;
import in.hefshine.quizsystem.entity.QuizSubmission;
import in.hefshine.quizsystem.exception.ResourceNotFoundException;
import in.hefshine.quizsystem.repository.QuizRepository;
import in.hefshine.quizsystem.service.QuestionService;
import in.hefshine.quizsystem.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionService questionService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
		quizService.createQuiz(quiz);
		return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
	}

	@PostMapping("/addQuiz")
	public ResponseEntity<String> addQuiz(@RequestBody Question question) {
		System.out.println("Received question with quiz ID: " + question.getQuiz().getId());
		if (question.getQuiz() != null && question.getQuiz().getId() > 0) {
			quizService.addQuiz(question);
			return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Quiz ID is missing or invalid!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Quiz getQuizDetails(@PathVariable Long id) throws ResourceNotFoundException {
		return quizService.getQuizById(id);
	}

	@PostMapping("/addQuizByTitle")
	public ResponseEntity<String> addQuizByTitle(@RequestBody Question question) {
		String quizTitle = question.getTitle();
		Quiz quiz = quizRepository.findByTitle(quizTitle);

		if (quiz == null) {
			return new ResponseEntity<>("Quiz with the given title does not exist!", HttpStatus.NOT_FOUND);
		}

		question.setQuiz(quiz);
		questionService.addQuestion(question);
		return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
	}

	@PostMapping("/submit")
	public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmission submission) {
	    try {
	        quizService.saveSubmission(submission);
	        return new ResponseEntity<>("Quiz submitted successfully!", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to submit quiz!", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
