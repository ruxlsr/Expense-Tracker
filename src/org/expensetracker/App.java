package org.expensetracker;

import org.expensetracker.controller.ExpenseController;

public class App {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        System.out.println("debut du programme");
        try {
            ExpenseController expenseController = new ExpenseController();
            expenseController.Command(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
