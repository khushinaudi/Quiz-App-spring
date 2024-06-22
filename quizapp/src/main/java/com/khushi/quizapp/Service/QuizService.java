package com.khushi.quizapp.Service;

import com.khushi.quizapp.Dao.QuestionDao;
import com.khushi.quizapp.Dao.QuizDao;
import com.khushi.quizapp.Model.Question;
import com.khushi.quizapp.Model.QuestionWrapper;
import com.khushi.quizapp.Model.Quiz;
import com.khushi.quizapp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//We want to create a Quiz and it should be stored in a DB. It should create a table which should have the quiz ready
//we have 2 tables -> QuizTable and QuestionTable, QuizTable is created from QuestionTable. We need to store this data in the DB as well.
//These tables need to have a Mapping. So that there's no repeated question
//1 to 1 - 1 quiz can have only 1 question
//1 to many - 1 quiz can have many questions but we cannot have same questions in different quiz.
//many to many - Different quizzes can have the same question. This is our case. In this, we need to create an extra table as well.

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //we first need to get the Questions, they are coming from QuestionDao, but we need specific number of questions and of the required category
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions); //questions are fetched from quizDao by running nativeQuery
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        //fetch the Quiz Object from the DB   //data might come or might not come -> hence Optional
        Optional<Quiz> quiz = quizDao.findById(id);   //we will get the questions in quiz obj but we need to convert these questions into QuestionWrapper

        //first lets get a hold on the QuizQuestions
        //when we use Optional, we first need to get the obj then we can get the questions
        List<Question> questionsFromDB = quiz.get().getQuestions();


        //Now we need to manually convert each question into questionWrapper
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.CREATED);
    }



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        //we can first get the quiz_questions using the id, it will give optional data so handle that using get
        Quiz quiz = quizDao.findById(id).get();

        //after getting the quiz, we need to get the questions from the list
        List<Question> questions = quiz.getQuestions();

        int right=0;
        int i=0;

        //compare the values in the response and in the questions
        //iterate from the list and check each ans, store it in a variable
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
