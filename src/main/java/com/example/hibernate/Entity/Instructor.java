package com.example.hibernate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    //@joinColumn is optional it uses for naming convention.It allows you to define column name for foreign key column.
    //default: instructor_detail_id
    @OneToOne(cascade = CascadeType.ALL)
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(String firstName, String lastName, String email, InstructorDetail instructorDetail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.instructorDetail = instructorDetail;
    }

    public Instructor(Integer id, String firstName, String lastName, String email, InstructorDetail instructorDetail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.instructorDetail = instructorDetail;
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

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (courses == null)
            courses = new ArrayList<>();

        course.setInstructor(this);
        courses.add(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(instructorDetail, that.instructorDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, instructorDetail);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}

