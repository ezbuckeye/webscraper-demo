package com.example.webscraperdemo.service;

import com.example.webscraperdemo.model.ExternalEvent;

import java.util.List;

public interface ScrapingServiceInterface {
    public List<ExternalEvent> getEvents();
}
