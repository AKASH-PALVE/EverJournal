package com.example.EverJournal.controller;

import com.example.EverJournal.model.QuoteResponse;
import com.example.EverJournal.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sentiment")
public class GeminiController {

    @Autowired
    GeminiService geminiService;

    @PostMapping("/{id}")
    public ResponseEntity<String> getSentimentAnalysis(@PathVariable Integer id){
        return geminiService.getSentimentAnalysis(id);
    }
}
