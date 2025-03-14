package com.example.quiz_service.Model;

import jakarta.persistence.*;

import java.util.List;
@Entity

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ElementCollection
    private List<Integer> questionsID;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getQuestionsID() {
        return questionsID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestionsID(List<Integer> questionsID) {
        this.questionsID = questionsID;
    }
}
