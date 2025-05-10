package com.example.EverJournal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeminiResponse {
    private ArrayList<Candidate> candidates;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Candidate{
        private Content content;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content{
        private ArrayList<Part> parts;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Part{
        private String text;
    }


}
