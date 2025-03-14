package com.moulik3.questionSe.Service;


import com.moulik3.questionSe.Model.Question;
import com.moulik3.questionSe.Model.QuestionWrapper;
import com.moulik3.questionSe.Model.Response;
import com.moulik3.questionSe.Repo.Questionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public List<Integer> getQuestionforQuiz(String difficultyLevel,Integer num){
        List<Integer> questions =QR.findByDifficulty(difficultyLevel,num);
        return questions;

}
public List<QuestionWrapper> getQuestionbyId(List<Integer> questionId){
        List<QuestionWrapper> q1 = new ArrayList<>();
        List<Question> q2= new ArrayList<>();
        for(Integer id :questionId){
            q2.add(QR.findById(id).get());
        }
        for(Question q :q2)
        {
          QuestionWrapper questionWrapper= new QuestionWrapper();
          questionWrapper.setId(q.getId());
          questionWrapper.setQuestionTitle(q.getQuestionTitle());
          questionWrapper.setOption1(q.getOption1());
            questionWrapper.setOption2(q.getOption2());
            questionWrapper.setOption3(q.getOption3());
            questionWrapper.setOption4(q.getOption4());
            q1.add(questionWrapper);

        }

        return q1;
}
public Integer getscore(List<Response> responses){
    int right=0;
    for (Response response:responses){
        Question question=QR.findById(response.getId()).get();
        if(response.getResponse().equals(question.getRightAnswer())){
            right++;
        }
    }
    return right;
}

}
