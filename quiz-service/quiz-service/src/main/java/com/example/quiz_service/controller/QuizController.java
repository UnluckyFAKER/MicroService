package com.example.quiz_service.controller;


import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Quiz;
import com.example.quiz_service.Model.QuizDto;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.Service.Producer.RabbitMqProducer;
import com.example.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService QS;
    @Autowired
    RabbitMqProducer rabbitMqProducer;
    @PostMapping("create")
    public ResponseEntity<String> createquiz(@RequestBody QuizDto quizDto){
        QS.createQuiz(quizDto.getDifficultyLevel(),quizDto.getNum(),quizDto.getTitle());
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
    @GetMapping("seequiz")
    public ResponseEntity<List<Quiz>>seequize(){
       return new ResponseEntity<>(QS.getAllQuiz(),HttpStatus.CREATED) ;

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquestion(@PathVariable Integer id){
    return new ResponseEntity<>(rabbitMqProducer.getQuiz(id),HttpStatus.OK);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getsubmit(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return new ResponseEntity<>(rabbitMqProducer.getCalculate(id, responses), HttpStatus.OK);
    }
}
