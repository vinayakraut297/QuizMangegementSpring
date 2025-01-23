package in.hefshine.quizsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.quizsystem.entity.Question;
import in.hefshine.quizsystem.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }
        
    @GetMapping("/title/{quizTitle}")
    public List<Question> getQuestionsByQuizTitle(@PathVariable String quizTitle) {
        return questionService.getQuestionsByQuizTitle(quizTitle);
    }



}
