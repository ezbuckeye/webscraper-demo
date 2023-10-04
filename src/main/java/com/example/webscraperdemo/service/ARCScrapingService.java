package com.example.webscraperdemo.service;

import com.example.webscraperdemo.model.ExternalEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ARCScrapingService implements ScrapingServiceInterface {
    // American Red Cross
    String domain = "https://volunteerconnection.redcross.org";
    String searchPath = "/?nd=rco_opportunity_browse_list&postal_code=43202";
    Document doc;

    public List<ExternalEvent> getEvents() {
        List<ExternalEvent> events = new ArrayList<>();
        try {
            // fetching the target website
            doc = Jsoup.connect(domain + searchPath).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements eventEles = doc.select("li").select(".red-cross-linklist__li").select("a");
        for(Element eventEle : eventEles) {
            ExternalEvent event = new ExternalEvent();
            String subPath = eventEle.attr("href");
            Document subDoc;
            try {
                subDoc = Jsoup.connect(domain + subPath).get();
            }catch(IOException e) {
                throw new RuntimeException(e);
            }
            event.setLink(domain +subPath);
            event.setTitle(eventEle.text());
            Element eventDetailEle = subDoc.select(".red-cross-detail").select("p").get(0);
            event.setDescription(eventDetailEle.text());
            events.add(event);
        }
        return events;
    }
}
