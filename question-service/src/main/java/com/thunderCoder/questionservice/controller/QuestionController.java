package com.thunderCoder.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.thunderCoder.questionservice.model.Question;
import com.thunderCoder.questionservice.model.QuestionWrapper;
import com.thunderCoder.questionservice.model.Response;
import com.thunderCoder.questionservice.service.QuestionService;

@Controller //graphql changes
public class QuestionController {
	
	@Autowired
	QuestionService service;
	
	@Autowired
	Environment environment;
	
	@QueryMapping
	public List<Question> getAllQuestions()
	{
		return service.getAllQuestions();
	}
	
	
	@QueryMapping
	public List<Question> getQuestionsByCategory(@Argument String category)
	{
		return service.getQuestionsByCategory(category);
	}
	
	
	@MutationMapping
	public String addQuestion(@Argument List<Question> question)
	{
		return service.addQuestion(question);
	}
	
	@QueryMapping
	public ResponseEntity<List<Integer>>  getQuesionsForQuiz
	(@Argument String categoryName,@Argument int noOfQuestions)
	{
		return service.getQuestionsForQuiz(categoryName,noOfQuestions);
	}

	@QueryMapping
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@Argument List<Integer> questionIds){
		System.out.println(environment.getProperty("local.server.port"));
		return service.getQuestionsFromId(questionIds);
	}
	
	@QueryMapping
	public ResponseEntity<Integer> getScore(@Argument List<Response> response )
	{
		return service.getScore(response);
	}
}
