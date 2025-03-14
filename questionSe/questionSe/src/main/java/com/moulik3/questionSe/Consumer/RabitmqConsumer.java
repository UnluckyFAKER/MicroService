package com.moulik3.questionSe.Consumer;

import com.moulik3.questionSe.Model.QuestionWrapper;
import com.moulik3.questionSe.Model.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabitmqConsumer {
    private static final Logger logger= LoggerFactory.getLogger(RabitmqConsumer.class);
    @RabbitListener(queues = "${queue.name}")
    public void getscore(Integer right){
        logger.info(String.format("###The Result of the quiz is %s",right));

    }
    @RabbitListener(queues = "${queue.json.name}")
    public void getscore(List<QuestionWrapper> qw){
        logger.info(String.format("### The Question in this quiz is %s",qw));

    }

}
