package com.moulik2.quizapp.Service;

import com.moulik2.quizapp.Model.Question;
import com.moulik2.quizapp.Repo.Questionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class questionService {
    @Autowired
    Questionrepo QR;
    public List<Question> getAllquestions(){
        List<Question> qu = QR.findAll();
        System.out.println("Fetched Questions: " + qu);
        return qu;
    }
    public List<Question> getByDiff(String d){
        List<Question> qu2=QR.findBydifficultyLevel(d);
        return qu2;
    }
//    public List<Question> getByoption(String d){
////        List<Question> qu2=QR.findByrightAnswer(d);
//        return qu2;
//    }
    public void add(Question question){
        QR.save(question);
    }

}
