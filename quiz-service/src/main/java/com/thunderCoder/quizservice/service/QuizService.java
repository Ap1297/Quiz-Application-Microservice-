package com.thunderCoder.quizservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thunderCoder.quizservice.dao.QuizDao;
import com.thunderCoder.quizservice.feign.QuizInterface;
import com.thunderCoder.quizservice.model.QuestionWrapper;
import com.thunderCoder.quizservice.model.Quiz;
import com.thunderCoder.quizservice.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	
	
	
	

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Integer> questions = quizInterface.getQuesionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		return  new ResponseEntity<>("Success",HttpStatus.CREATED);
	}



	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(List<Integer> id) {
		return quizInterface.getQuestionsFromId(id);
	}



	public ResponseEntity<Integer> calculteResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> result = quizInterface.getScore(responses);
		return  result;
	}
	
	
	
}
