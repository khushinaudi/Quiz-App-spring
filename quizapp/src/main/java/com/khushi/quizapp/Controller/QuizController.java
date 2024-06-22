package com.khushi.quizapp.Controller;

import com.khushi.quizapp.Model.Question;
import com.khushi.quizapp.Model.QuestionWrapper;
import com.khushi.quizapp.Model.Response;
import com.khushi.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

//    http://localhost:8243/quiz/create?category=Java&numQ=5&title=JQuiz
    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }
    //after POST, mySQL  will now have 3 tables: question, quiz, quiz_questions in "questionDb"
    //quiz_questions has quiz_id and questions_id




    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){ //We will return QuestionWrapper without the answer
        //We want to get the quiz now but without the answer as we don't want to show the answer to the client
        //So now we will create a QuestionWrapper which will have all the columns except the answer
        return quizService.getQuizQuestions(id);
    }



    //to calculate the scores
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }

}
