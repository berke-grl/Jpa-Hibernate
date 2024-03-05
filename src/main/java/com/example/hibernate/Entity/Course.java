package com.example.hibernate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String title;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Instructor instructor;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    /*
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
     If you don't specify the @JoinTable annotation,
     JPA will use default naming conventions to generate the join table and column names,
     */
    private List<Student> students;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }

    public Course(String title, Instructor instructor, List<Review> reviews) {
        this.title = title;
        this.instructor = instructor;
        this.reviews = reviews;
    }

    public Course(Integer id, String title, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addReview(Review review) {
        if (reviews == null)
            reviews = new ArrayList<>();
        review.setCourse(this);
        reviews.add(review);
    }

    public void addStudent(Student student) {
        if (students == null)
            students = new ArrayList<>();
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(instructor, course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, instructor);
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", instructor=" + instructor +
                //", reviews=" + reviews +
                ", students=" + students +
                '}';
    }
}
