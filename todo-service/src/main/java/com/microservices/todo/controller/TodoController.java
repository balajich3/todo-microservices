package com.microservices.todo.controller;

import com.microservices.todo.client.CalendarClient;
import com.microservices.todo.event.TodoCreatedEvent;
import com.microservices.todo.kafka.TodoProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final CalendarClient calendarClient;
    private final TodoProducer todoProducer;
    public TodoController(CalendarClient calendarClient, TodoProducer todoProducer) {
        this.calendarClient = calendarClient;
        this.todoProducer = todoProducer;
    }
    @GetMapping
    public List<String> getTodos(){
        return List.of("Learn Spring Boot", "Learn Microservices", "Learn Docker", "Learn Kubernetes");
    }
    @GetMapping("/{id}")
    public String getTodoById(@PathVariable int id){
        return "Todo ID"+ id;
    }
    @GetMapping("/events")
    @RateLimiter(name="calendarService")
    @Retry(name="calendarService")
    @CircuitBreaker(name="calendarService", fallbackMethod="calendarFallback")
    public List<String> getCalendarEvents(){
        return calendarClient.getEvents();
    }
    public List<String> calendarFallback(Exception exception ){
        return List.of("Calendar Service is down");
    }
    @PostMapping
    public String createTodo(@RequestBody TodoCreatedEvent event) {
        todoProducer.sendTodoEvent(event);
        return "Todo created and event published successfully";
    }
}
