package com.opuscapita.demo.products.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "course")
    Set<StudentCourse> studentCourses=new HashSet<>();

//    @ManyToMany(mappedBy = "courseSet")
//    Set<Student> studentSet=new HashSet<>();


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

    //    public Set<Student> getStudentSet() {
//        return studentSet;
//    }

//    public void setStudentSet(Set<Student> studentSet) {
//        this.studentSet = studentSet;
//    }
}
