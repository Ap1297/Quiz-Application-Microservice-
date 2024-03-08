package com.thunderCoder.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
	public String categoryName;
	public int noOfQuestions;
	public String title;
}
