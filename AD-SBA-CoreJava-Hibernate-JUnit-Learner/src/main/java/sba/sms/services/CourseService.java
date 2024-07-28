package sba.sms.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 *
 *
 * this java file interacts with the database, perform CRUD operations with
 * transaction management and exception handling
 */
public class CourseService implements CourseI {
    public CourseService() {}
    @Override
    public void createCourse(Course course) {   //persist course to database, also handle commit,rollback, and exceptions
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();  //begin session transaction
            session.persist(course);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback(); //if transaction is not null then roll all changes back
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public Course getCourseById(int courseId) {  //return course if exists, also handle commit,rollback, and exceptions
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Course course = new Course();   //if course doesnt exist create new course
        try {
            tx = session.beginTransaction();
            Query<Course> query = session.createQuery("from Course where id = :id", Course.class);   //declare variable named query of type Query<> represented in the db
            query.setParameter("id", courseId);
            course = query.getSingleResult();
            tx.commit();
        }
         catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
         }
        finally {
            session.close();
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() { //returns a list of courses
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Course> courseList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query<Course> query = session.createQuery("from Course", Course.class);
            courseList = query.getResultList();
            tx.commit();
        }
        catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return courseList;
    }

}
