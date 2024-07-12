package com.bansira.assignment.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private int publicationYear;
    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
