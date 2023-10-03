package com.example.webscraperdemo.service;

import com.example.webscraperdemo.model.ExternalEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


@Service
public class WebScrapingService {

    Document doc;

    List<String> urls = new ArrayList<>();

    public List<ExternalEvent> getEvent() {
        initializeURLRepo();

        for(String url : urls) {
            try {
                // fetching the target website
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(doc);
        }
        return null;
    }

    private void initializeURLRepo() {
        urls.add("https://ymcaohio.volunteermatters.org/project-catalog");
    }
}
