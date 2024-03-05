package com.example.hibernate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    /*
@JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "student_id),
        inverseJoinColumns = @JoinColumn(name = "course_id")
)
 If you don't specify the @JoinTable annotation,
 JPA will use default naming conventions to generate the join table and column names,
 */
    private List<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (courses == null)
            courses = new ArrayList<>();
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
