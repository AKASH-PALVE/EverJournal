package com.example.EverJournal.service;

import com.example.EverJournal.model.Journal;
import com.example.EverJournal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    JournalRepository journalRepository;

    public ResponseEntity<List<Journal>> getAllJournals() {
        return new ResponseEntity<>(journalRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addJournal(Journal journal) {
        journalRepository.save(journal);
        return new ResponseEntity<>("note added" , HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateJournal(Journal journal) {
        journalRepository.save(journal);
        return new ResponseEntity<>("note updated" , HttpStatus.CREATED);
    }


    public ResponseEntity<String> deleteJournal(Integer id) {
        journalRepository.deleteById(id);
        return new ResponseEntity<>("note deleted" , HttpStatus.OK);
    }
}
