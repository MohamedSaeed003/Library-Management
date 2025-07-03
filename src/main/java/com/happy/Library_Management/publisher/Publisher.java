package com.happy.Library_Management.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happy.Library_Management.book.Book;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    private Set<Book> books;
}
