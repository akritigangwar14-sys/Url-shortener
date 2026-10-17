package com.example.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    // Custom query method to find a mapping by its short key
    Optional<UrlMapping> findByShortKey(String shortKey);
}
