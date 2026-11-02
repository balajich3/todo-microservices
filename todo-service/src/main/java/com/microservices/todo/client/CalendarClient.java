package com.microservices.todo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="calendar-service")
public interface CalendarClient {

    @GetMapping("/events")
    List<String> getEvents();
}
