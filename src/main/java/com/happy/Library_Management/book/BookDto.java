package com.happy.Library_Management.book;

import lombok.Data;
import java.util.List;

@Data
public class BookDto {
    private String title;
    private String isbn;
    private int publicationYear;
    private int edition;
    private String language;
    private String summary;
    private String bookCover;
    private List<Long> authors;
    private List<Long> categories;
    private Long publisher;
}
