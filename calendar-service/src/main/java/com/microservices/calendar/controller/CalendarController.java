package com.microservices.calendar.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class CalendarController {

    @GetMapping
    public List<String> getEvents() {
        return List.of("Team Meeting", "Microservices Practice", "Project Review");
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable int id) {
        return "Event ID: " + id;
    }
}