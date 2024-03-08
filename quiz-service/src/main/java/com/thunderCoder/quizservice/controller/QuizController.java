package com.thunderCoder.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thunderCoder.quizservice.model.QuestionWrapper;
import com.thunderCoder.quizservice.model.QuizDto;
import com.thunderCoder.quizservice.model.Response;
import com.thunderCoder.quizservice.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService service;
	
	@PostMapping("Create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		return service.createQuiz(quizDto.getCategoryName(), quizDto.getNoOfQuestions(), quizDto.getTitle());
	}
	
	
	@PostMapping("get")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestBody List<Integer> id)
	{
		return service.getQuizQuestions(id);
	}
	
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
	{
		return service.calculteResult(id,responses);
	}
	
	
}
