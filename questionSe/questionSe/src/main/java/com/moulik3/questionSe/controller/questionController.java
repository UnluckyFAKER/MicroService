package com.moulik3.questionSe.controller;


import com.moulik3.questionSe.Model.Question;
import com.moulik3.questionSe.Model.QuestionWrapper;
import com.moulik3.questionSe.Model.Response;
import com.moulik3.questionSe.Service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class questionController {
    @Autowired
    Environment environment;

    @Autowired
    private questionService qs;

    @GetMapping("/allquestion")
    public ResponseEntity<List<Question>> quiz(){
        List<Question> li = qs.getAllquestions();
        System.out.println("Fetched Questions: " + li); // Debugging log
        try {
            return new ResponseEntity<>(li, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping("/difficultyLevel/{difficultyLevel}") // use @pathvariable to acess the variable
    public List<Question> diffi(@PathVariable String difficultyLevel) {
        return qs.getByDiff(difficultyLevel);
    }
    @PostMapping("add")
    public ResponseEntity<String> addquestion(@RequestBody Question question )//use @RequestBody to add boby in model
    {
        qs.add(question);
        return new ResponseEntity<>("Done",HttpStatus.CREATED);
    }
    //generate quiz
    //get question using quiz id
    //calculate result
    //here we generates the question and give the id as return
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionforQuiz(@RequestParam String difficultyLevel ,@RequestParam Integer a){
        return new ResponseEntity<>(qs.getQuestionforQuiz(difficultyLevel,a),HttpStatus.OK);
    }
    //here we get question from the ids
    @PostMapping("getquestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionbyids(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(qs.getQuestionbyId(questionIds),HttpStatus.CREATED);
    }
    //here we calculate the score
    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return new ResponseEntity<>(qs.getscore(responses),HttpStatus.ACCEPTED);
    }

}
