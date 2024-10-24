package org.expensetracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpensesList {
    List<Expense> expenses;

    public ExpensesList() {
        this.expenses = new ArrayList<Expense>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void deleteExpense(int id) {
        expenses.remove(id);
    }

    public void updateDescription(int id, Optional<String> description) {
        description.ifPresent((value) -> {
            if (!value.isEmpty()) {
                expenses.get(id).setDescription(value);
            } else {
                System.err.println("Error: Empty description");
            }

        });

    }

    public void updateAmount(int id, Optional<String> amount) {

        amount.ifPresent((value) -> {
            if (!value.isEmpty()) {
                expenses.get(id).setAmount(Integer.parseInt(value));
            } else {
                System.err.println("Error: Empty amount");
            }
        });

    }
}
