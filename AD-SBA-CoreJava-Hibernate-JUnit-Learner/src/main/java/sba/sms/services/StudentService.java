package sba.sms.services;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method. Lombok @Log used to
 * generate a logger file.
 */

public class StudentService implements StudentI {

    private static final CourseService courseService = new CourseService();

    public StudentService() {
    }

    @Override
    public List<Student> getAllStudents() {  //returns a list of students
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Student> studentList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query<Student> q = session.createQuery("from Student", Student.class);
            studentList = q.getResultList();
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return studentList;
    }

    @Override
    public void createStudent(Student student) {  //creates a student
        Session session = HibernateUtil.getSessionFactory().openSession(); //opens a hibernate session & creates a new session one to interact with db
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Student getStudentByEmail(String email) {  //returns a student name based on email
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Student s = null;
        try {
            tx = session.beginTransaction();
            //  Query<Student> q = session.createQuery("from Student where email = :email", Student.class);
            //    q.setParameter("email", email);
            //  student = q.getSingleResult();
            s = session.get(Student.class, email);
            tx.commit();
        } catch (Exception exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return s;
    }

    @Override
    public boolean validateStudent(String email, String password) { //confirms student email & password, returns whether or not theres a match
        Student student = getStudentByEmail(email);
        return student != null && student.getPassword().equals(password);
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {  //registers a student to a course
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = getStudentByEmail(email);
            student.addCourse(courseService.getCourseById(courseId));
            session.merge(student);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Course> getStudentCourses(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Course> courseList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            String getStudentCourse = "Select c.id, c.name, c.instructor from course as c join student_courses as sc on c.id = sc.course_id join student as s on s.email = sc.student_email where s.email = :email";
            NativeQuery<Course> studentCourses = session.createNativeQuery(getStudentCourse, Course.class);
            studentCourses.setParameter("email", email);
            courseList = studentCourses.getResultList();
            //Student student = getStudentByEmail(email);
            //String nativeGetStudentCourses = "SELECT c.id AS id, c.name AS name, c.instructor AS instructor "
           // + "FROM course AS c " + "JOIN student_courses AS sc ON c.id = sc.courses_id "
           // + "JOIN student AS s ON s.email = sc.student_email "
           // + "WHERE s.email = :email";
            //NativeQuery<Course> studentCourses = session.createNativeQuery(nativeGetStudentCourses, Course.class);
            //studentCourses.setParameter("email", email);
            //courseList = studentCourses.getResultList();
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return courseList;
    }
}

