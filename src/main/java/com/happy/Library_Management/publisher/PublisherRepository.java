package com.happy.Library_Management.publisher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    boolean existsByPublisherName(String publisherName);
}
