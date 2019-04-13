package com.shnu.part.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue
    private Long id;
    private Double score;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
