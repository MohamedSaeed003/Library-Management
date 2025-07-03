package com.happy.Library_Management.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happy.Library_Management.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Book> books;
}
