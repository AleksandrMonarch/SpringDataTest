package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SCIENTIFIC_PUBLICATION")
@Getter
@Setter
public class ScientificPublication {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "PUBLICATION_DATE")
    private Date publicationDate;

    @Column(name = "PUBLICATION_NUMBER")
    private Long publicationNumber;

    @Column(name = "PUBLICATION_MAGAZINE")
    private String publicationMagazine;

    @Column(name = "PUBLICATION_THEME")
    private String publicationTheme;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;
}
