package com.example.hibernate.Entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;
    @ManyToOne
    private Course course;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public Review(String comment, Course course) {
        this.comment = comment;
        this.course = course;
    }

    public Review(Integer id, String comment, Course course) {
        this.id = id;
        this.comment = comment;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
