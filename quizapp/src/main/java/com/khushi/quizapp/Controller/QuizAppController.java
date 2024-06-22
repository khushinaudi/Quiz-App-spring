package com.khushi.quizapp.Controller;

import com.khushi.quizapp.Model.Question;
import com.khushi.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuizAppController {

    @Autowired
    QuestionService questionService;


    //for fetching all the questions/data
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        //this will return a list of Objects of "Question" class, since there are multiple questions
        //this is ResponseEntity cause we return status code also in this, see in QuestionService. The job of service is to respond with status codes
        return questionService.getAllQuestions();
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
        //why we have taken Question in the parameter
        //we want to receive a question only and not a text file (csv) and then manually from the code we fetch each value
        //from the client side, lets only receive json
        //spring says "you just specify the object, i will convert that to json"
        //use @RequestBody cause you will be adding the data in the Body
    }


}
