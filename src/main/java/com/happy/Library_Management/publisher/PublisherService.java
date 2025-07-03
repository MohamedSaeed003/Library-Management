package com.happy.Library_Management.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher getById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public Publisher create(Publisher publisher) {
        if (publisherRepository.existsByPublisherName(publisher.getPublisherName())) {
            throw new RuntimeException("Publisher already exists");
        }
        return publisherRepository.save(publisher);
    }

    public Publisher update(Long id, Publisher updated) {
        Publisher existing = getById(id);
        existing.setPublisherName(updated.getPublisherName());
        return publisherRepository.save(existing);
    }

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}
