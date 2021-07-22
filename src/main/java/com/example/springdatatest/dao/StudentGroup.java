package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STUDENT_GROUP")
@Getter
@Setter
public class StudentGroup {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name = "GROUP_SIZE")
    private Long groupSize;

    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY,
            mappedBy = "studentGroup")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        student.setStudentGroup(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setStudentGroup(null);
    }
}
