package com.moulik2.quizapp.Repo;

import com.moulik2.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
