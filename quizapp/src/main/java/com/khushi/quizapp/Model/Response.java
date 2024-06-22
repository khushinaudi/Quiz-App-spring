package com.khushi.quizapp.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//response class is used for calculating the scores on the basis of quiz responses submitted by user
//it needs quiz_id and the list of responses 
@Data
@RequiredArgsConstructor
public class Response {
    private String id;
    private String response;
}
