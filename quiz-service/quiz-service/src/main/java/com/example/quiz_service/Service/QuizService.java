package com.example.quiz_service.Service;

import com.example.quiz_service.Model.Question;
import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Quiz;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.Repo.QuizRepo;
import com.example.quiz_service.feign.QuestionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionInterface QI;

    public void createQuiz(String difficultyLevel, int num, String title) {
        List<Integer> questions = QI.getQuestionforQuiz(difficultyLevel,num).getBody();
//
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsID(questions);
        quizRepo.save(quiz); // Ensure questions are properly linked before saving
    }
    public List<Quiz> getAllQuiz(){
       List<Quiz> q =quizRepo.findAll();
       return q;
    }
//   public List<QuestionWrapper> getQuiz(Integer id) {
//       // fetch the quiz from db
//       Quiz quiz = quizRepo.findById(id).get();
//       List<Integer> ids = quiz.getQuestionsID();
//       List<QuestionWrapper> qw = QI.getQuestionbyids(ids).getBody();
//       return qw;
//
//
//   }

//    public int getCalculate(Integer id, List<Response> responses){
//        int right=QI.getScore(responses).getBody();
//        return right;
//    }
}
