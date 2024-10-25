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
        if (command.length == 0) {
            System.err.println("Invalid  argument");
            return;
        }
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

                if (!CommandValidator.checkAddingArgs(arg1, arg2, arg3, arg4)) {
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
                    JsonData.saveInFileJsonData(expensesList);

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
                    JsonData.saveInFileJsonData(expensesList);
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
                if (command.length > 7) {
                    System.err.println("Invalid number of argument");
                    return;
                }
                switch (command.length) {
                    case 5 -> {
                        String arg1 = command[1], arg2 = command[2], arg3 = command[3], arg4 = command[4];
                        if (!CommandValidator.checkUpdateOneAttribute(arg1, arg2, arg3, arg4)) {
                            return;
                        }

                        String description = null, amount = null;
                        String id;

                        switch (arg1) {
                            case "--description" -> {
                                description = arg2;
                                id = arg4;
                                expensesList.updateDescription(Integer.parseInt(id), description);
                            }

                            case "--id" -> {
                                id = arg1;
                                if (arg3.equals("--amount")) {
                                    amount = arg4;
                                    expensesList.updateAmount(Integer.parseInt(id), amount);
                                } else {
                                    expensesList.updateDescription(Integer.parseInt(id), description);
                                    description = arg4;
                                }
                            }

                            case "--amount" -> {
                                amount = arg2;
                                id = arg4;
                                expensesList.updateAmount(Integer.parseInt(id), amount);
                            }

                            default -> {
                                System.err.println("Invalid argument");
                                return;
                            }
                        }

                    }

                    case 7 -> {
                        String arg1 = command[1], arg2 = command[2], arg3 = command[3], arg4 = command[4],
                                arg5 = command[5],
                                arg6 = command[6];

                        if (!CommandValidator.checkUpdatingArgs(arg1, arg2, arg3, arg4, arg5, arg6)) {
                            return;
                        }

                        String id = null;
                        String description = null;
                        String amount = null;

                        switch (arg1) {
                            case "--description" -> {
                                description = arg2;

                                switch (arg3) {
                                    case "--id" -> {
                                        amount = arg6;
                                        id = arg4;
                                    }

                                    case "--amount" -> {
                                        amount = arg4;
                                        id = arg6;
                                    }

                                    default -> {
                                        System.err.println("Invalid argument");
                                        return;
                                    }
                                }

                            }

                            case "--amount" -> {
                                amount = arg2;
                                switch (arg3) {
                                    case "--id" -> {
                                        description = arg6;
                                        id = arg4;
                                    }

                                    case "--description" -> {
                                        description = arg4;
                                        id = arg6;
                                    }

                                    default -> {
                                        System.err.println("Invalid argument");
                                        return;
                                    }
                                }
                            }

                            case "--id" -> {
                                id = arg2;
                                switch (arg3) {
                                    case "--amount" -> {
                                        description = arg6;
                                        amount = arg4;
                                    }

                                    case "--description" -> {
                                        description = arg4;
                                        amount = arg6;
                                    }

                                    default -> {
                                        System.err.println("Invalid argument");
                                        return;
                                    }
                                }
                            }
                            default -> {
                                System.err.println("Invalid argument");
                                return;
                            }
                        }

                        expensesList.updateAmount(Integer.parseInt(id), amount);
                        expensesList.updateDescription(Integer.parseInt(id), description);
                    }

                    default -> {
                        System.err.println("missing argument");
                    }
                }
                JsonData.saveInFileJsonData(expensesList);

            }

            default -> {
                System.out.printf("Invalid command");
            }
        }
    }

}
