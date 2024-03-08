package com.thunderCoder.quizservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thunderCoder.quizservice.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
	
	

}
