package com.thunderCoder.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thunderCoder.questionservice.model.Question;
import com.thunderCoder.questionservice.model.QuestionWrapper;
import com.thunderCoder.questionservice.model.Response;
import com.thunderCoder.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService service;
	
	@Autowired
	Environment environment;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return service.getAllQuestions();
	}
	
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
	{
		return service.getQuestionsByCategory(category);
	}
	
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody List<Question> question)
	{
		return service.addQuestion(question);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>>  getQuesionsForQuiz
	(@RequestParam String categoryName,@RequestParam int noOfQuestions)
	{
		return service.getQuestionsForQuiz(categoryName,noOfQuestions);
	}

	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		System.out.println(environment.getProperty("local.server.port"));
		return service.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response )
	{
		return service.getScore(response);
	}
}