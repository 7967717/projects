package com.rr.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @author roman.rudenko on 25-May-16.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
    Collection<Note> findByUserUsername(String username);
}
