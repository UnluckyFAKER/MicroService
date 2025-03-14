package com.moulik2.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moulik2.quizapp.Service.questionService;
import com.moulik2.quizapp.Model.Question;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class questionController {  // Class name should start with uppercase

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

}
