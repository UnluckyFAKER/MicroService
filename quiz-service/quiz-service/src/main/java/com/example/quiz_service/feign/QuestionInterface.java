package com.example.quiz_service.feign;

import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// it connect the perticular microservice
@FeignClient("QUESTIONSERVICE")
public interface QuestionInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionforQuiz(@RequestParam String difficultyLevel , @RequestParam Integer a);
    //here we get question from the ids
    @PostMapping("question/getquestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionbyids(@RequestBody List<Integer> questionIds);
    //here we calculate the score
    @PostMapping("/question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
