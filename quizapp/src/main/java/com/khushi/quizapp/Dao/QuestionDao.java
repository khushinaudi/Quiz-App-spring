package com.khushi.quizapp.Dao;

import com.khushi.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //Dao means Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {


    //just define a function here in Dao, data jpa is smart enough to fetch this
    // as it already knows category column exists in the table
    public List<Question> findByCategory(String category);
    

    //jpa can get the data for you but upto a certain point only, we will have to write native SQL query (JPQL query) here to fetch the data
    @Query(value="SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
    


}
