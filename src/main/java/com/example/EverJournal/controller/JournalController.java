package com.example.EverJournal.controller;

import com.example.EverJournal.model.Journal;
import com.example.EverJournal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping("/")
    public ResponseEntity<List<Journal>> getAllJournals(){
        return journalService.getAllJournals();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addJournal(@RequestBody Journal journal){
        return journalService.addJournal(journal);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateJournal(@RequestBody Journal journal){
        return journalService.updateJournal(journal);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable Integer id){
        return journalService.deleteJournal(id);
    }



}
