
package com.microservices.calendar.kafka;

import com.microservices.calendar.event.TodoCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TodoConsumer {

    @KafkaListener(
            topics = "todo-created",
            groupId = "calendar-group"
    )
    public void consumeTodoEvent(TodoCreatedEvent event) {

        System.out.println("Received Todo Event:");
        System.out.println("Todo ID: " + event.getId());
        System.out.println("Todo Title: " + event.getTitle());
    }
}
