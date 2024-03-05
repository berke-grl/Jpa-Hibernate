package com.example.hibernate;

import com.example.hibernate.DAO.AppDAO;
import com.example.hibernate.Entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return run -> {
            //create(appDAO);
            //findInstructorById(appDAO);
            //deleteById(appDAO);
            //findInstructorDetailById(appDAO);
            //deleteInstructorDetailById(appDAO);
            //createInstructorWithCourse(appDAO);
            //findCoursesByInstructorId(appDAO);
            //findCoursesByInstructorIdJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updateCourse(appDAO);
            //deleteInstructorById(appDAO);
            //deleteCourseById(appDAO);
            //saveCourse(appDAO);
            //findCourseAndReviewsByCourseId(appDAO);
            //deleteCourseAndReviewsByCourseId(appDAO);
            //createCourseAndStudent(appDAO);
            findCourseAndStudentByCourseId(appDAO);
        };
    }

    private void create(AppDAO appDAO) {
        InstructorDetail instructorDetail = new InstructorDetail("www.youtube/aktn", "Guitar");
        Instructor instructor = new Instructor("Aktan", "Sanhal", "aktan@gmail.com", instructorDetail);

        appDAO.save(instructor);

        System.out.println("-- INSTRUCTOR SAVED --");
    }

    private void findInstructorById(AppDAO appDAO) {
        Instructor searched = appDAO.findInstructorById(8);
        InstructorDetail detail = searched.getInstructorDetail();
        //this might not work because OneToMany's default fetch type is LAZY!
        List<Course> courseList = searched.getCourses();

        System.out.println("SEARCHED INSTRUCTOR => " + searched);
        System.out.println("SEARCHED INSTRUCTOR DETAIL => " + detail);
        System.out.println("INSTRUCTOR'S COURSES : " + courseList);
    }

    private void deleteById(AppDAO appDAO) {
        appDAO.deleteById(3);
        System.out.println("DELETED RECORD ID => 3");
    }

    private void findInstructorDetailById(AppDAO appDAO) {

        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(3);
        System.out.println("INSTRUCTOR DETAIL => " + instructorDetail);

        //bi-directional part
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println("INSTRUCTOR => " + instructor);
    }

    private void deleteInstructorDetailById(AppDAO appDAO) {
        appDAO.deleteInstructorDetailById(4);
    }

    private void createInstructorWithCourse(AppDAO appDAO) {
        Course course = new Course("Piano");
        Course course2 = new Course("Guitar");
        Course course3 = new Course("jazz");
        InstructorDetail instructorDetail = new InstructorDetail("www.youtube/numan", "Music");
        Instructor instructor = new Instructor("Beyonce", "Songari", "beyonce@gmail.com");

        instructor.setInstructorDetail(instructorDetail);

        instructor.addCourse(course);
        instructor.addCourse(course2);
        instructor.addCourse(course3);

        appDAO.save(instructor);
    }

    private void findCoursesByInstructorId(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorById(8);
        instructor.setCourses(appDAO.findCoursesByInstructorId(instructor.getId()));
        System.out.println("Instructor is => " + instructor);
        //ManyToOne's default fetch type is EAGER!
        System.out.println("Instructor's courses are => " + instructor.getCourses());
    }

    private void findCoursesByInstructorIdJoinFetch(AppDAO appDAO) {
        Instructor instructor = appDAO.findCoursesByInstructorIdJoinFetch(8);
        System.out.println("Instructor is => " + instructor);
        //OneToOne's default fetch type is eager!
        System.out.println("Instructor details are => " + instructor.getInstructorDetail());
        //Join Fetch: Even the OneToMany's fetch type join fetch will work similar to eager loading!
        System.out.println("Instructor's courses are => " + instructor.getCourses());
    }

    private void updateInstructor(AppDAO appDAO) {
        Instructor oldInstr = appDAO.findInstructorById(8);
        System.out.println("OLD INSTRUCTOR : " + oldInstr);
        oldInstr.setLastName("Atreus");
        appDAO.updateInstructor(oldInstr);
        System.out.println("UPDATED INSTRUCTOR : " + oldInstr);
    }

    private void updateCourse(AppDAO appDAO) {
        Course oldcourse = appDAO.findCourseById(8);
        System.out.println("OLD COURSE : " + oldcourse);
        oldcourse.setTitle("Violin");
        appDAO.updateCourse(oldcourse);
        System.out.println("UPDATED COURSE : " + oldcourse);
    }

    private void deleteInstructorById(AppDAO appDAO) {
        System.out.println("DELETED INSTRUCTOR : " + appDAO.findInstructorById(7));
        appDAO.deleteInstructorById(7);
    }

    private void deleteCourseById(AppDAO appDAO) {
        System.out.println("DELETED COURSE : " + appDAO.findCourseById(7));
        appDAO.deleteCourseById(7);
    }

    private void saveCourse(AppDAO appDAO) {
        Course course = new Course("Swimming");
        Review review = new Review("This curse is private for kids");
        course.addReview(review);
        //This will add review thanks to the cascade type
        appDAO.save(course);
    }

    private void findCourseAndReviewsByCourseId(AppDAO appDAO) {
        System.out.println("SEARCHED COURSE AND REVIEWS: " + appDAO.findCourseAndReviewsByCourseId(10));
    }

    private void deleteCourseAndReviewsByCourseId(AppDAO appDAO) {
        appDAO.deleteCourseAndReviewsByCourseId(10);
        System.out.println("DELETED COURSE AND REVIEWS ID: 10");
        System.out.println("01.02.2024");
        System.out.println("01.02.2024");
        System.out.println("01.02.2024");
        System.out.println("01.02.2024");
        System.out.println("01.02.2024");
        System.out.println("01.02.2024");
    }

    private void createCourseAndStudent(AppDAO appDAO) {
        Course course = new Course("Painting Lessons");
        Student student = new Student("Gokhan", "Gurel", "gkn@hotmail.com");
        course.addStudent(student);
        //we do not need to save student separately because of the cascade type of student attribute in Course entity class
        appDAO.save(course);
    }

    private void findCourseAndStudentByCourseId(AppDAO appDAO) {
        Course course = appDAO.findCourseAndStudentByCourseId(11);
        System.out.println("Course and Course Students : " + course);
    }
}
