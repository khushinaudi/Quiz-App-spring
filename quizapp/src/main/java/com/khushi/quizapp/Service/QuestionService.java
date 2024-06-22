package com.khushi.quizapp.Service;

import com.khushi.quizapp.Dao.QuestionDao;
import com.khushi.quizapp.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);  //findAll is already present in JPA repository, gives us the list of all Questions so our job here is done
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);  //if error is encountered, mark the status as BAD_REQUEST
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
            //findAll is already present in JPA repository, gives us the list of all Questions so our job here is done
            //this function is defined in QuestionDao, just find by Category
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question); //first we will save and then we will return ResponseEntity
            return new ResponseEntity<>("success", HttpStatus.CREATED);   //in JPA, "save" is the same as "add"   //"save" is used for "update" as well
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);   //"success" should be returned from service itself, we also have Status.codes for this - 200, 404
    }
}
