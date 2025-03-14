package com.moulik2.quizapp.controller;

import com.moulik2.quizapp.Model.QuestionWrapper;
import com.moulik2.quizapp.Model.Quiz;
import com.moulik2.quizapp.Model.Response;
import com.moulik2.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moulik2.quizapp.Model.Question;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService QS;
    @PostMapping("create")
    public ResponseEntity<String> createquiz(@RequestParam String difficultyLevel,@RequestParam int num,@RequestParam String title ){
        QS.createQuiz(difficultyLevel,num,title);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
    @GetMapping("seequiz")
    public ResponseEntity<List<Quiz>>seequize(){
       return new ResponseEntity<>(QS.getAllQuiz(),HttpStatus.CREATED) ;

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquestion(@PathVariable Integer id){
    return new ResponseEntity<>(QS.getQuiz(id),HttpStatus.OK);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getsubmit(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return new ResponseEntity<>(QS.getCalculate(id, responses), HttpStatus.OK);
    }
}
