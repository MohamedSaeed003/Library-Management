package com.happy.Library_Management.book;

import com.happy.Library_Management.author.Author;
import com.happy.Library_Management.author.AuthorRepository;
import com.happy.Library_Management.category.Category;
import com.happy.Library_Management.category.CategoryRepository;
import com.happy.Library_Management.publisher.Publisher;
import com.happy.Library_Management.publisher.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    public Book createBook(BookDto dto) {
        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Book with this ISBN already exists");
        }

        Book book = new Book();
        setBookFieldsFromDto(book, dto);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDto dto) {
        Book book = getBookById(id);
        setBookFieldsFromDto(book, dto);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // 🔁 Reusable private method
    private void setBookFieldsFromDto(Book book, BookDto dto) {
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setEdition(dto.getEdition());
        book.setLanguage(dto.getLanguage());
        book.setSummary(dto.getSummary());
        book.setBookCover(dto.getBookCover());

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.getAuthors()));
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategories()));
        Publisher publisher = publisherRepository.findById(dto.getPublisher())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublisher(publisher);
    }
}