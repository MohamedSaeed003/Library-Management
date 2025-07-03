package com.happy.Library_Management.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happy.Library_Management.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<Book> books;
}
