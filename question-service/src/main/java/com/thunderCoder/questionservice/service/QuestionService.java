package com.thunderCoder.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.thunderCoder.questionservice.dao.QuestionDao;
import com.thunderCoder.questionservice.model.Question;
import com.thunderCoder.questionservice.model.QuestionWrapper;
import com.thunderCoder.questionservice.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao dao;
	
	public List<Question> getAllQuestions()
	{
		try {
			return dao.findAll();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Question> getQuestionsByCategory(String category)
	{
		try {
			return dao.findByCategory(category);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String addQuestion(@RequestBody List<Question> question)
	{
		for(Question data:question)
			dao.save(data);
		return "success";
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int noOfQuestions) {
		List<Integer> questions = dao.findRandomQuestionsByCategory(categoryName, noOfQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer id: questionIds)
		{
			questions.add(dao.findById(id).get());
		}
		for(Question question: questions)
		{
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		for(Response response: responses)
		{
			Question question = dao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
				right++;
			 
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
	
	
}
