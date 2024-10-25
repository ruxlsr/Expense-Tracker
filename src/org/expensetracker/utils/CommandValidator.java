package org.expensetracker.utils;

public class CommandValidator {
    public static boolean checkAddingArgs(String arg1, String arg2, String arg3, String arg4) {
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

    public static boolean checkUpdatingArgs(String arg1, String arg2, String arg3, String arg4, String arg5,
            String arg6) {
        // Group arguments into pairs for easier processing
        String[][] args = { { arg1, arg2 }, { arg3, arg4 }, { arg5, arg6 } };

        for (String[] pair : args) {
            if (pair[0].isEmpty()) {
                System.err.println("Syntax Error: Empty argument");
                return false;
            }

            if (pair[0].equals("--amount")) {
                try {
                    int amount = Integer.parseInt(pair[1]);
                    if (amount < 0) {
                        System.err.println("Invalid Amount value");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Amount value: Not a number");
                    return false;
                }
            } else if (pair[0].equals("--id")) {
                try {
                    int id = Integer.parseInt(pair[1]);
                    if (id < 0) {
                        System.err.println("Invalid id value");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid id value: Not a number");
                    return false;
                }
            } else if (!pair[0].equals("--description")) {
                System.err.println("Invalid argument: " + pair[0]);
                return false;
            }
        }

        return true;
    }

    public static boolean validateTwoArguments(String argName, String arg1, String arg2) {
        if (arg1.isEmpty() || arg2.isEmpty()) {
            System.err.println("Syntax Error: Empty argument");
            return false;
        }

        if (!arg1.equals(argName)) {
            System.err.println("Syntax Error: invalid command name");
            return false;
        }

        return true;
    }
}
