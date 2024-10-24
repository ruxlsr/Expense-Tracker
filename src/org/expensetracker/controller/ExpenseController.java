package org.expensetracker.controller;

import java.io.IOException;

import org.expensetracker.model.Expense;
import org.expensetracker.model.ExpensesList;
import org.expensetracker.utils.JsonData;

public class ExpenseController {
    ExpensesList expensesList;

    public ExpenseController() throws Exception {
        expensesList = new ExpensesList();
        expensesList.setExpenses(JsonData.getDataFromJsonFile());
    }

    public void Command(String[] command) {
        switch (command[0]) {
            case "add" -> {
                String arg1 = command[1];
                String arg2 = command[2];
                String arg3 = command[3];
                String arg4 = command[4];

                if (!validateArguments(arg1, arg2, arg3, arg4)) {
                    return;
                }

                String description = null;
                String amount = null;

                switch (arg1) {
                    case "--description":
                        description = arg2;
                        amount = arg4;
                        break;
                    case "--amount":
                        amount = arg2;
                        description = arg4;
                        break;
                    default:
                        System.err.println("Invalid argument");
                        return;
                }
                expensesList.addExpense(description, Integer.parseInt(amount));
                try {
                    JsonData.saveInFileJsonData(expensesList.getExpenses());

                    System.out.printf("Expense added successfully (ID: %d)\n",
                            expensesList.getExpenses().getLast().getId());
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            case "delete" -> {
                System.out.printf("Expense deleted successfully\n");
            }
            case "update" -> {
                System.out.printf("Expense deleted successfully\n");
            }
            case "list" -> {
                System.out.printf("Listing\n");
            }

            case "summary" -> {
                System.out.printf("Total expenses: $20\n");
            }

            default -> {
                System.out.printf("Default cases");
            }
        }
    }

    private boolean validateArguments(String arg1, String arg2, String arg3, String arg4) {
        if (arg1.isEmpty() || arg3.isEmpty()) {
            System.err.println("Syntax Error: Empty argument");
            return false;
        }

        if (arg1.equals("--amount") && Integer.parseInt(arg2) < 0) {
            System.err.println("Invalid Amount value");
            return false;
        }

        if (arg3.equals("--amount") && Integer.parseInt(arg4) < 0) {
            System.err.println("Invalid Amount value");
            return false;
        }

        if (!arg1.equals("--description") && !arg1.equals("--amount") && !arg3.equals("--description")
                && !arg3.equals("--amount")) {
            System.err.println("Invalid argument");
            return false;
        }

        return true;
    }
}
