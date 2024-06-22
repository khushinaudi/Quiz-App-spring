package com.khushi.quizapp.Model;
import com.khushi.quizapp.Model.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//QuizTable can have:
//1. primary key
//2. title
//3. questions


@Entity //since its a table
@Data  //for Lombok : Getter and Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToMany
    private List<Question> questions; //1 quiz can have multiple questions

}
