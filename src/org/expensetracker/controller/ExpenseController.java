package org.expensetracker.controller;

import java.io.IOException;

import org.expensetracker.model.Expense;
import org.expensetracker.model.ExpensesList;
import org.expensetracker.utils.JsonData;
import org.expensetracker.utils.CommandValidator;

public class ExpenseController {
    ExpensesList expensesList;

    public ExpenseController() throws Exception {
        expensesList = new ExpensesList();
        expensesList.setExpenses(JsonData.getDataFromJsonFile());
    }

    public void Command(String[] command) throws Exception {
        switch (command[0]) {
            case "add" -> {
                if (command.length != 5) {
                    System.err.println("Invalid number of argument");
                    return;
                }
                String arg1 = command[1];
                String arg2 = command[2];
                String arg3 = command[3];
                String arg4 = command[4];

                if (!CommandValidator.validateAddingArguments(arg1, arg2, arg3, arg4)) {
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
                if (command.length != 3) {
                    System.err.println("Invalid Argument: ");
                    return;
                }
                String arg1 = command[1];
                String arg2 = command[2];

                if (!CommandValidator.validateTwoArguments("--id", arg1, arg2)) {
                    return;
                }

                try {
                    expensesList.deleteExpense(Integer.parseInt(arg2));
                    JsonData.saveInFileJsonData(expensesList.getExpenses());
                    System.out.printf("Expense deleted successfully\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "list" -> {
                if (command.length != 1) {
                    System.err.println("Invalid number of argument");
                    return;
                }
                expensesList.listExpenses();
            }
            case "summary" -> {
                int summary = 0;

                if (command.length == 3) {
                    String arg1 = command[1];
                    String arg2 = command[2];
                    if (!CommandValidator.validateTwoArguments("--month", arg1, arg2)) {
                        return;
                    }
                    int month = Integer.parseInt(arg2);
                    System.out.println("le mois " + month);
                    summary = expensesList.getExpenses().stream()
                            .filter(expense -> expense.getExpenseDate().getMonthValue() == month)
                            .mapToInt(Expense::getAmount)
                            .sum();
                } else if (command.length == 1) {
                    summary = expensesList.getExpenses().stream().mapToInt(Expense::getAmount).sum();
                } else {
                    System.err.println("Invalid number of argument");
                    return;
                }

                System.out.printf("Total expenses: $%d\n", summary);
            }
            case "update" -> {
                if (command.length < 7) {
                    System.err.println("Invalid number of argument");
                    return;
                }
                String arg1 = command[1];
                String arg2 = command[2];
                String arg3 = command[3];
                String arg4 = command[4];
                String arg5 = command[5];
                String arg6 = command[6];

                if (!CommandValidator.validateAddingArguments(arg1, arg2, arg3, arg4)) {
                    return;
                }

                if (!arg5.equals("--id") || arg6.isEmpty()) {
                    System.err.println("Invalid  id argument");
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
                // TODO("Test this");
                expensesList.updateAmount(Integer.parseInt(arg6), amount);
                expensesList.updateDescription(Integer.parseInt(arg6), description);

            }

            default -> {
                System.out.printf("Invalid command");
            }
        }
    }

}
