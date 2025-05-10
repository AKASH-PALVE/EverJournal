package com.example.EverJournal.controller;

import com.example.EverJournal.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @GetMapping("")
    public ResponseEntity<String> getQuote(){
        return quoteService.getQuote();
    }

}
