package com.example.quiz_service.Repo;

import com.example.quiz_service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
