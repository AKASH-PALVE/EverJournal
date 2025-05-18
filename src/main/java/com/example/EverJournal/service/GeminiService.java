package com.example.EverJournal.service;

import com.example.EverJournal.model.GeminiRequest;
import com.example.EverJournal.model.GeminiResponse;
import com.example.EverJournal.model.QuoteResponse;
import com.example.EverJournal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class GeminiService {

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    RestTemplate restTemplate;

    // API SETUP
    private static final String api_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=GEMINI_API_KEY";
    private static final String api_key = "YOUR_GEMINI_API_KEY";


    public ResponseEntity<String> getSentimentAnalysis(Integer id) {


        String final_api_url = api_url
                .replace("GEMINI_API_KEY",api_key);

        // Build the request

        // Building the prompt
        // 1. get the note data [ done ]
        // 2. format of analysis to be received [ done ]
        // 3. example format of analysis [ done ]


        String journalData = journalRepository.findById(id).get().toString();
        String journalSentimentAnalysisFormat = "Analyze the above journal entry and respond in a clear, readable, user-friendly format. Include:\n" +
                "\n" +
                "1. \uD83D\uDCCC Summary: A short, meaningful summary of the journal (1-2 lines).\n" +
                "2. \uD83D\uDE0A Sentiment: Overall emotion (Positive, Neutral, or Negative).\n" +
                "3. \uD83C\uDFAF Mood Score: A number between -1.0 and 1.0 representing the mood.\n" +
                "4. ✨ Key Insight: A personal insight or takeaway from the journal.\n" +
                "\n" +
                "Journal Entry:\n" +
                "Title: %s  \n" +
                "Description: %s\n" +
                "Only include this nothing else ";
        String journalSentimentAnalysisFormatExample = "\uD83D\uDCDD Summary: Started the day with a peaceful walk that brought clarity and focus.\n" +
                "\n" +
                "\uD83D\uDE0A Sentiment: Positive\n" +
                "\n" +
                "\uD83C\uDFAF Mood Score: 0.85\n" +
                "\n" +
                "✨ Key Insight: A quiet morning routine can greatly improve mental clarity and overall mood.\n";

        String customPrompt = journalData + "\n\n" + journalSentimentAnalysisFormat + "\nExample:\n" + journalSentimentAnalysisFormatExample;

        GeminiRequest.Part part = new GeminiRequest.Part(customPrompt);
        ArrayList<GeminiRequest.Part> partArrayList = new ArrayList<>();
        partArrayList.add(part);

        GeminiRequest.Content content = new GeminiRequest.Content(partArrayList);
        ArrayList<GeminiRequest.Content> contentArrayList = new ArrayList<>();
        contentArrayList.add(content);

        GeminiRequest request = new GeminiRequest();
        request.setContents(contentArrayList);

        // setting http headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        // setup the request entity
        HttpEntity<GeminiRequest> entity = new HttpEntity<>(request,headers);

        // seding the request

        ResponseEntity<GeminiResponse> responseEntity = restTemplate.exchange(
                final_api_url,
                HttpMethod.POST,
                entity,
                GeminiResponse.class
        );

        if(responseEntity.getStatusCode() == HttpStatus.OK){

            String MSG =responseEntity.getBody()
                    .getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

            return new ResponseEntity<>(MSG,HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed" , HttpStatus.NOT_FOUND);

    }
}
