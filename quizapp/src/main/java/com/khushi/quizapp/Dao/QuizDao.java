package com.khushi.quizapp.Dao;

import com.khushi.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer > {  //now we see we need to create a class "Quiz" as well
}
