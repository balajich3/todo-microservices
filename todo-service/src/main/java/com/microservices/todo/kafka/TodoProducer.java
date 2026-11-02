package com.microservices.todo.kafka;

import com.microservices.todo.event.TodoCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodoProducer {
    private static final String TOPIC = "todo-created";
    private final KafkaTemplate<String, TodoCreatedEvent> kafkaTemplate;
    public TodoProducer(
            KafkaTemplate<String, TodoCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendTodoEvent(TodoCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event.getId(), event);
        System.out.println("Published JSON Kafka event: " + event.getTitle());
    }
}