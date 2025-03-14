package com.example.quiz_service.Service.Producer;

import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Quiz;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.Repo.QuizRepo;
import com.example.quiz_service.feign.QuestionInterface;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitMqProducer {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionInterface QI;
    @Value("${exchange.name}")
    private String exchange;
    @Value("${key.name}")
    private String key;
    @Autowired
    RabbitTemplate rabbitTemplate;
    private static Logger logger= LoggerFactory.getLogger(RabbitMqProducer.class);
    public int getCalculate(Integer id, List<Response> responses) {
        logger.info("Calculating score for quiz ID: {}", id);

        // Call question-service and handle possible null response
        ResponseEntity<Integer> response = QI.getScore(responses);
        if (response == null || response.getBody() == null) {
            logger.error("Error: Received null response from QuestionInterface.getScore()");
            return 0; // Default score if question-service fails
        }

        int right = response.getBody();
        logger.info("The correct Answer is -> {}", right);

        try {
            // Send message to RabbitMQ
            rabbitTemplate.convertAndSend(exchange, key, right);
            logger.info("Successfully sent score {} to RabbitMQ", right);
        } catch (Exception e) {
            logger.error("Failed to send message to RabbitMQ: {}", e.getMessage(), e);
        }

        return right;
    }

    @Value("${key.json.name}")
    private String jkey;

    public List<QuestionWrapper> getQuiz(Integer id) {
        // fetch the quiz from db
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> ids = quiz.getQuestionsID();
        List<QuestionWrapper> qw = QI.getQuestionbyids(ids).getBody();
        System.out.println("The Quiz Started " + qw);
        rabbitTemplate.convertAndSend(exchange, jkey, qw);
        return qw;


    }



}
