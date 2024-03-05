package com.example.hibernate.DAO;

import ch.qos.logback.core.CoreConstants;
import com.example.hibernate.Entity.Course;
import com.example.hibernate.Entity.Instructor;
import com.example.hibernate.Entity.InstructorDetail;
import com.example.hibernate.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Instructor tmp = entityManager.find(Instructor.class, id);
        entityManager.remove(tmp);
    }

    @Override
    public InstructorDetail findInstructorDetailById(Integer id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(Integer id) {
        InstructorDetail tmp = entityManager.find(InstructorDetail.class, id);

        //when you remove InstructorDetail then automatically you must set null instructor_detail_id column in Instructor table
        Instructor instructor = entityManager.find(Instructor.class, id);
        instructor.setInstructorDetail(null);

        entityManager.remove(tmp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :instructor_id", Course.class);
        query.setParameter("instructor_id", id);
        return query.getResultList();
    }

    @Override
    public Instructor findCoursesByInstructorIdJoinFetch(Integer id) {
        TypedQuery<Instructor> query =
                entityManager.createQuery(
                        "SELECT i FROM Instructor i join fetch i.courses join fetch i.instructorDetail where i.id = :instructor_id", Instructor.class);
        query.setParameter("instructor_id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer id) {
        Instructor instructor = findInstructorById(id);
        //YOU MUST BREAK ASSOCİATİON OF ALL INSTRUCTOR'S COURSES BEFORE DELETE
        List<Course> courses = instructor.getCourses();
        for (Course c : courses) {
            c.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(Integer id) {
        Course course = findCourseById(id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c join fetch c.reviews where c.id = :review_id", Course.class
        );
        query.setParameter("review_id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteCourseAndReviewsByCourseId(Integer id) {
        Course tmp = findCourseById(id);
        //we do not need to SET NULL VALUES FOR REVIEW'S COURSE_ID BECAUSE OF THE CASCADE TYPE
        entityManager.remove(tmp);
    }

    @Override
    public Course findCourseAndStudentByCourseId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c join fetch c.students where c.id = :searched_id",
                Course.class
        );
        query.setParameter("searched_id", id);
        return query.getSingleResult();
    }
}
