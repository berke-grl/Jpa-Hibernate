package com.example.hibernate.DAO;

import com.example.hibernate.Entity.Course;
import com.example.hibernate.Entity.Instructor;
import com.example.hibernate.Entity.InstructorDetail;
import com.example.hibernate.Entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(Integer id);

    void deleteById(Integer id);

    InstructorDetail findInstructorDetailById(Integer id);

    void deleteInstructorDetailById(Integer id);

    List<Course> findCoursesByInstructorId(Integer id);

    Instructor findCoursesByInstructorIdJoinFetch(Integer id);

    void updateInstructor(Instructor instructor);

    Course findCourseById(Integer id);

    void updateCourse(Course course);

    void deleteInstructorById(Integer id);

    void deleteCourseById(Integer id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(Integer id);

    void deleteCourseAndReviewsByCourseId(Integer id);

    Course findCourseAndStudentByCourseId(Integer id);
}
