package com.moulik3.questionSe.Repo;

import com.moulik3.questionSe.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// it takes 2 thing 1st entity class and the primary key type
public interface Questionrepo extends JpaRepository<Question,Integer> {
    List<Question> findBydifficultyLevel(String difficultyLevel);

    @Query(value = "SELECT q.id FROM question q WHERE q.difficultylevel ILIKE ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Integer> findByDifficulty(String difficultyLevel, int num);



}