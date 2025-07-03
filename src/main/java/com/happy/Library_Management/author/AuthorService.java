package com.happy.Library_Management.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author createAuthor(Author author) {
        if (authorRepository.existsByFullName(author.getFullName())) {
            throw new RuntimeException("Author already exists");
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updated) {
        Author existing = getById(id);
        existing.setFullName(updated.getFullName());
        return authorRepository.save(existing);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
