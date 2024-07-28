package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString.Exclude;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */
@NoArgsConstructor
//@AllArgsConstructor   not working
//@RequiredArgsConstructor  switching stuff around
@Setter
@Getter
@ToString
@Entity
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE) //sets default access levels to private for all fields in class


public class Student {

@NonNull
@Column(length = 50, name = "name")
    String name;

@Id
@NonNull
    @Column(length = 50, name = "email")
    String email;

@NonNull
    @Column(length = 50, name = "password")
    String password;

@Exclude

@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
@JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_email"), inverseJoinColumns = @JoinColumn(name = "course_id"))

Set<Course> courses;

public void addCourse(Course course) {  //addcourse method adds course object in the argument to course list then student to
    courses.add(course);
    course.getStudents().add(this);
}

    @Override
    public boolean equals(Object o) { //equals method used to compare object o and object this
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(password, student.password);
    }
    @Override
    public int hashCode() {
    return Objects.hash(name, email, password);
    }

    public Student (String email, String name, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
    }

}





