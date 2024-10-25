package org.expensetracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public void deleteExpense(int id) throws Exception {
        if (expenses.size() < id) {
            throw new Exception("id not existant");
        }
        expenses.remove(id - 1);
    }

    /**
     * @param id
     * @param description
     */
    public void updateDescription(int id, String description) {
        if (description.isEmpty()) {
            System.err.println("Error: Empty description");
            return;
        }

        expenses.get(id - 1).setDescription(description);

    }

    /**
     * @param id
     * @param amount
     */
    public void updateAmount(int id, String amount) {

        if (amount.isEmpty()) {
            System.err.println("Error: Empty amount");
            return;
        }

        expenses.get(id - 1).setAmount(Integer.parseInt(amount));

    }

    public void listExpenses() {
        System.out.printf("%-5s %-10s %-20s %-7s\n", "id", "Date", "Description", "amount");

        expenses.forEach(expenses -> {
            System.out.printf("%-5d %-10s %-20s $%-7d\n", expenses.getId(), expenses.getExpenseDate().toString(),
                    expenses.getDescription(), expenses.getAmount());
        });
    }
}
