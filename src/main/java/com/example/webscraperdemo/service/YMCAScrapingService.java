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
public class YMCAScrapingService implements ScrapingServiceInterface{

    private String domain = "https://ymcaohio.volunteermatters.org";
    private String searchPath = "/project-catalog";

    public ExternalEvent fillEvent(ExternalEvent event) {
        Document doc;
        try {
            doc = Jsoup.connect(event.getLink()).get();
        } catch (IOException e) {
            return event;
        }
        Element eventInfo = doc.selectFirst("div.project-info");
        event.setDescription(eventInfo.selectFirst("#description").text());

        return event;
    }

    public List<ExternalEvent> getEvents() {
        List<ExternalEvent> extEvents = new ArrayList<>();
        Document doc;

        try {
            doc = Jsoup.connect(domain + searchPath).get();
        } catch (IOException e) {
            // Return empty list if failed to connect
            return extEvents;
        }
        Elements eventElements = doc.select("div.project-entry");
        for (Element eventElement : eventElements){
            ExternalEvent event = new ExternalEvent();
            event.setTitle(eventElement.select("span.project-name").text());
            event.setLink(domain + eventElement.select("a.project-page").attr("href"));
            extEvents.add(fillEvent(event));
        }
        return extEvents;
    }
}
