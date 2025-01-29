//	package in.hefshine.quizsystem.controller;
//	
//	import in.hefshine.quizsystem.controller.QuizController.QuizSubmission;
//	
//	
//	import java.util.List;
//	
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.web.bind.annotation.CrossOrigin;
//	import org.springframework.web.bind.annotation.GetMapping;
//	import org.springframework.web.bind.annotation.PathVariable;
//	import org.springframework.web.bind.annotation.PostMapping;
//	import org.springframework.web.bind.annotation.RequestBody;
//	import org.springframework.web.bind.annotation.RequestMapping;
//	import org.springframework.web.bind.annotation.RestController;
//	
//	import in.hefshine.quizsystem.entity.Question;
//	import in.hefshine.quizsystem.entity.Quiz;
//	import in.hefshine.quizsystem.entity.QuizSubmission;
//	import in.hefshine.quizsystem.exception.ResourceNotFoundException;
//	import in.hefshine.quizsystem.repository.QuizRepository;
//	import in.hefshine.quizsystem.service.QuestionService;
//	import in.hefshine.quizsystem.service.QuizService;
//	@RestController
//	@RequestMapping("/api/quiz")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public class QuizController {
//		@Autowired
//		private QuizService quizService;
//	
//		@Autowired
//		private QuizRepository quizRepository;
//	
//		@Autowired
//		private QuestionService questionService;
//	
//		@PostMapping("/create")
//		public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
//			quizService.createQuiz(quiz);
//			return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
//		}
//	
//		@PostMapping("/addQuiz")
//		public ResponseEntity<String> addQuiz(@RequestBody Question question) {
//			System.out.println("Received question with quiz ID: " + question.getQuiz().getId());
//			if (question.getQuiz() != null && question.getQuiz().getId() > 0) {
//				quizService.addQuiz(question);
//				return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
//			} else {
//				return new ResponseEntity<>("Quiz ID is missing or invalid!", HttpStatus.BAD_REQUEST);
//			}
//		}
//	
//		@GetMapping("/getAll")
//		public ResponseEntity<List<Quiz>> getAllQuizzes() {
//			return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
//		}
//	
//		@GetMapping("/{id}")
//		public Quiz getQuizDetails(@PathVariable Long id) throws ResourceNotFoundException {
//			return quizService.getQuizById(id);
//		}
//	
//		@PostMapping("/addQuizByTitle")
//		public ResponseEntity<String> addQuizByTitle(@RequestBody Question question) {
//			String quizTitle = question.getTitle();
//			Quiz quiz = quizRepository.findByTitle(quizTitle);
//	
//			if (quiz == null) {
//				return new ResponseEntity<>("Quiz with the given title does not exist!", HttpStatus.NOT_FOUND);
//			}
//	
//			question.setQuiz(quiz);
//			questionService.addQuestion(question);
//			return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
//		}
//	
//		@PostMapping("/submit")
//		public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmission submission) {
//		    try {
//		        quizService.saveSubmission(submission);
//		        return new ResponseEntity<>("Quiz submitted successfully!", HttpStatus.OK);
//		    } catch (Exception e) {
//		        return new ResponseEntity<>("Failed to submit quiz!", HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//		}
//		
//		 public static class QuizSubmission {
//		        private String quizTitle;
//		        private List<Integer> answers; // List of selected answer indexes
//	
//		        // Getters and setters
//		        public String getQuizTitle() {
//		            return quizTitle;
//		        }
//	
//		        public void setQuizTitle(String quizTitle) {
//		            this.quizTitle = quizTitle;
//		        }
//	
//		        public List<Integer> getAnswers() {
//		            return answers;
//		        }
//	
//		        public void setAnswers(List<Integer> answers) {
//		            this.answers = answers;
//		        }
//		    }
//	
//	}

package in.hefshine.quizsystem.controller;

import in.hefshine.quizsystem.entity.QuizSubmission; // Correct import
import in.hefshine.quizsystem.entity.Question;
import in.hefshine.quizsystem.entity.Quiz;
import in.hefshine.quizsystem.exception.ResourceNotFoundException;
import in.hefshine.quizsystem.repository.QuizRepository;
import in.hefshine.quizsystem.service.QuestionService;
import in.hefshine.quizsystem.service.QuizResult;
import in.hefshine.quizsystem.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionService questionService;

//	@PostMapping("/create")
//	public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
//		quizService.createQuiz(quiz);
//		return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
//	}
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
	    try {
	        quizService.createQuiz(quiz);
	        return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
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
		System.out.println("Received Quiz Title: " + submission.getQuizTitle());
		System.out.println("Received Answers: " + submission.getAnswers());
		try {
			if (submission.getQuizTitle() == null || submission.getAnswers() == null
					|| submission.getAnswers().isEmpty()) {
				return new ResponseEntity<>("Invalid submission data!", HttpStatus.BAD_REQUEST);
			}
			quizService.saveSubmission(submission); 
			return new ResponseEntity<>("Quiz submitted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to submit quiz!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping("/results")
	public ResponseEntity<QuizResult> getQuizResults(@RequestParam String quizTitle, @RequestParam String answersJson) {
	    try {
	        Map<Long, Integer> answers = new ObjectMapper().readValue(answersJson, new TypeReference<Map<Long, Integer>>() {});
	        QuizResult result = quizService.getQuizResults(quizTitle, answers);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/deleteByTitle/{title}")
	public ResponseEntity<String> deleteQuizByTitle(@PathVariable String title) {
	    try {
	        quizService.deleteQuizByTitle(title);
	        return new ResponseEntity<>("Quiz deleted successfully!", HttpStatus.OK);
	    } catch (ResourceNotFoundException e) {
	        return new ResponseEntity<>("Quiz not found!", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to delete quiz!", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
