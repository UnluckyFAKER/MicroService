package com.moulik2.quizapp.Service;

import com.moulik2.quizapp.Model.QuestionWrapper;
import com.moulik2.quizapp.Model.Response;
import com.moulik2.quizapp.Repo.Questionrepo;
import com.moulik2.quizapp.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moulik2.quizapp.Model.Question;
import com.moulik2.quizapp.Model.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    Questionrepo questionrepo;
    public void createQuiz(String difficultyLevel, int num, String title) {
        List<Question> questions = questionrepo.findByDifficulty(difficultyLevel, num);

        // Debugging: Print the retrieved questions
        System.out.println("Retrieved Questions: " + questions);

        if (questions == null || questions.isEmpty()) {
            throw new RuntimeException("No questions found for difficulty level: " + difficultyLevel);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepo.save(quiz); // Ensure questions are properly linked before saving
    }
    public List<Quiz> getAllQuiz(){
       List<Quiz> q =quizRepo.findAll();
       return q;
    }
    public List<QuestionWrapper> getQuiz(Integer id){
        // fetch the quiz from db
        Optional<Quiz> quiz=quizRepo.findById(id);
        // fetch the question from the quiz to list of question
        List<Question> questionfromdB =quiz.get().getQuestions();
        List<QuestionWrapper>qw=new ArrayList<>();
        for(Question q :questionfromdB){
            // add the question to question wrapper
            QuestionWrapper f = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            qw.add(f);
        }

        return qw;
    }
    public int getCalculate(Integer id, List<Response> responses){
        Optional<Quiz> quiz=quizRepo.findById(id);
        List<Question> questionfromdB =quiz.get().getQuestions();
        int i=0;
        int right=0;
        for (Response response:responses){
            if(response.getResponse().equals(questionfromdB.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return right;
    }
}
