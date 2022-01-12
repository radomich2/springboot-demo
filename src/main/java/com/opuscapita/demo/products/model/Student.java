package com.opuscapita.demo.products.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;



    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER
        ,cascade = CascadeType.ALL)
    Set<StudentCourse> studentCourses=new HashSet<>();

//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(
//        name = "student_course",
//        joinColumns = @JoinColumn(name = "student_id"),
//        inverseJoinColumns = @JoinColumn(name = "course_id"))
//    Set<Course> courseSet=new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

//   public Set<Course> getCourseSet() {
 //       return courseSet;
 //   }

   // public void setCourseSet(Set<Course> courseSet) {
   //     this.courseSet = courseSet;
  //  }
}
