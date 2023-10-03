package com.example.webscraperdemo.controller;

import com.example.webscraperdemo.model.ExternalEvent;
import com.example.webscraperdemo.service.WebScrapingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@Controller
public class WebScraperController {
    WebScrapingService wss;

    public WebScraperController(WebScrapingService webScrapingService){
        this.wss = webScrapingService;
    }

    @GetMapping("/scrape")
    public @ResponseBody ExternalEvent getExternalEvents() {
        wss.getEvent();
        return new ExternalEvent("1", "2", "3");
    }
}
