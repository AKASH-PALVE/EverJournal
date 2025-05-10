package com.example.EverJournal.repository;

import com.example.EverJournal.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal,Integer> {
}
