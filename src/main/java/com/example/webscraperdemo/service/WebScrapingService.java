package com.example.webscraperdemo.service;

import com.example.webscraperdemo.WebScraperDemoApplication;
import com.example.webscraperdemo.model.ExternalEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


@Service
public class WebScrapingService implements ScrapingServiceInterface{

    List<ScrapingServiceInterface> scrapingServices;

    public WebScrapingService(ARCScrapingService arcScrapingService, YMCAScrapingService ymcaScrapingService) {
        scrapingServices = new ArrayList<>();
        scrapingServices.add(arcScrapingService);
        scrapingServices.add(ymcaScrapingService);
    }

    public List<ExternalEvent> getEvents() {
        List<ExternalEvent> events = new ArrayList<>();
        for(ScrapingServiceInterface ss : scrapingServices) {
            events.addAll(ss.getEvents());
        }
        return events;
    }
}
