package com.microservices.expenses.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @GetMapping
    public List<String> getExpenses() {
        return List.of("Food - 250", "Travel - 500", "Shopping - 1000");
    }

    @GetMapping("/{id}")
    public String getExpenseById(@PathVariable int id) {
        return "Expense ID: " + id;
    }
}