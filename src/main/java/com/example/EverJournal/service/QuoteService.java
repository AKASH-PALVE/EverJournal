package com.example.EverJournal.service;

import com.example.EverJournal.model.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {


    @Autowired
    RestTemplate restTemplate;

    private static final String API_URL = "https://api.api-ninjas.com/v1/quotes?X-Api-Key=YOUR_API_KEY";
    private static final String API_KEY= "wBpjbmBmGnMJUKfr8KQbnA==6vnVGnZsBg67OMd6";


    public ResponseEntity<String> getQuote() {

        String FINAL_API_URL = API_URL
                .replace("YOUR_API_KEY",API_KEY);

        try {
            ResponseEntity<QuoteResponse[]> apiResponse = restTemplate.exchange(FINAL_API_URL, HttpMethod.GET, null, QuoteResponse[].class);
            QuoteResponse[] quoteResponseBody = apiResponse.getBody();


            if (quoteResponseBody != null && quoteResponseBody.length > 0){
                QuoteResponse quoteResponse = quoteResponseBody[0];


                String MSG = " ' " + quoteResponse.getQuote() + " ' "+
                        "\n"+
                              "    - " +   quoteResponse.getAuthor();
                        ;

                return new ResponseEntity<>(MSG, HttpStatus.OK);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Something went wrong !", HttpStatus.NOT_FOUND);
    }
}
