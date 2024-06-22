package com.khushi.quizapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data  //since we are using Lombok, we don't need to define Getter and Setters here
@Entity  //we want this class to get mapped to the SQL table
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //to generate the IDs automatically -> IDENTITY: mysql will generate these, it will be unique and incremental
    private Integer id;
    private String category;
    private String difficultylevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;
    private String rightAnswer;



}
