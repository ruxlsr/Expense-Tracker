package org.expensetracker.utils;

public class CommandValidator {
    public static boolean validateAddingArguments(String arg1, String arg2, String arg3, String arg4) {
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

    public static boolean validateTwoArguments(String commandName, String arg1, String arg2) {
        if (arg1.isEmpty() || arg2.isEmpty()) {
            System.err.println("Syntax Error: Empty argument");
            return false;
        }

        if (!arg1.equals(commandName)) {
            System.err.println("Syntax Error: invalid command name");
            return false;
        }

        return true;
    }
}
