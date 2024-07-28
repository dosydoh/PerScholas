package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */
@NoArgsConstructor
// @AllArgsConstructor  doesnt work
//@RequiredArgsConstructor   ////might not work
@Setter
@Getter
@ToString
@Entity
@Table(name = "course")

    public class Course {
@Id
@NonNull
@GeneratedValue(strategy = GenerationType.IDENTITY)   //Primary key , generation type IDENTITY,
@Column(name = "id")
private int id;


@Column(length = 50, name = "name")  //50 character limit, not null, name name
private String name;
@NonNull

@Column(length = 50, name = "instructor")  //50 character limit not null
private String instructor;

@ToString.Exclude  //excludes certain generated fields

@ManyToMany(mappedBy = "courses", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
Set<Student> students; //java persistence api (jpa) & hibernate, defines m2m relatonship btwn entities in the db, research fetchtype

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && name.equals(course.name) && instructor.equals(course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructor);
    }

    public Course (String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }
}


