package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STUDENT")
@Setter
@Getter
public class Student {

//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "ID")
//    private String id;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
//    @MapsId
    @JoinColumn(name = "PASSPORT_ID", referencedColumnName = "ID")
    private Passport passport;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY/*,
            optional = false*/)
    @JoinColumn(name = "GRADE_ID", referencedColumnName = "ID")
    private Grade grade;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY/*, optional = false*/)
    @JoinColumn(name = "HOME_ADDRESS_ID", referencedColumnName = "ID")
    private HomeAddress homeAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY/*, optional = false*/)
    @JoinColumn(name = "STUDENT_GROUP_ID", referencedColumnName = "ID")
    private StudentGroup studentGroup;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "student")
    private List<ScientificPublication> scientificPublications = new ArrayList<>();

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private List<Course> courses = new ArrayList<>();

    public void addScientificPublication(ScientificPublication scientificPublication) {
        scientificPublications.add(scientificPublication);
        scientificPublication.setStudent(this);
    }

    public void removeScientificPublication(ScientificPublication scientificPublication) {
        scientificPublications.remove(scientificPublication);
        scientificPublication.setStudent(null);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", passport=" + passport +
                '}';
    }
}
