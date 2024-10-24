package org.expensetracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpensesList {
    List<Expense> expenses;

    public ExpensesList() {
        this.expenses = new ArrayList<Expense>();
    }

    /**
     * @return List<Expense>
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * @param expenses
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * @param expense
     */
    public void addExpense(String description, int amount) {
        LocalDate ld = LocalDate.now();
        expenses.add(new Expense(expenses.getLast().getId() + 1, description, amount,
                LocalDate.of(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth())));
    }

    /**
     * @param id
     */
    public void deleteExpense(int id) {
        expenses.remove(id);
    }

    /**
     * @param id
     * @param description
     */
    public void updateDescription(int id, Optional<String> description) {
        description.ifPresent((value) -> {
            if (!value.isEmpty()) {
                expenses.get(id).setDescription(value);
            } else {
                System.err.println("Error: Empty description");
            }

        });

    }

    /**
     * @param id
     * @param amount
     */
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
