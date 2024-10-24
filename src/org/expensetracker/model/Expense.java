package org.expensetracker.model;

import java.time.LocalDate;

public class Expense {
    private int id;
    private String description;
    private int amount;
    private LocalDate expenseDate;

    @SuppressWarnings("unused")
    private Expense() {
    }

    public Expense(int id, String description, int amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.expenseDate = date;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
