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

    public static boolean checkUpdateOneAttribute(String arg1, String arg2, String arg3, String arg4) {
        if (arg1.isEmpty() || arg3.isEmpty()) {
            System.err.println("Syntax Error: Empty argument");
            return false;
        }

        // Group arguments into pairs for easier processing
        String[][] args = { { arg1, arg2 }, { arg3, arg4 } };

        // Track the types of arguments encountered (null if none yet)
        String idArg = null;
        String otherArg = null;

        for (String[] pair : args) {
            if (pair[0].isEmpty()) {
                System.err.println("Syntax Error: Empty argument");
                return false;
            }

            if (pair[0].equals("--id")) {
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
                idArg = "--id"; // Record the id argument
            } else if (pair[0].equals("--amount")) {
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
                otherArg = "--amount"; // Record the other argument
            } else if (pair[0].equals("--description")) {
                otherArg = "--description"; // Record the other argument

            } else {
                System.err.println("Invalid argument: " + pair[0]);
                return false;
            }
        }

        // Check for valid combinations: either "--id" and "--amount" or "--id" and
        // "--description"
        if (idArg != null && (otherArg == "--amount" || otherArg == "--description")) {
            return true;
        } else {
            System.err
                    .println("Invalid combination of arguments.  Must have --id and either --amount or --description.");
            return false;
        }
    }

    public static boolean checkUpdatingArgs(String arg1, String arg2, String arg3, String arg4, String arg5,
            String arg6) {
        // Group arguments into pairs for easier processing
        String[][] args = { { arg1, arg2 }, { arg3, arg4 }, { arg5, arg6 } };

        // Track which argument types have been encountered
        boolean hasId = false;
        boolean hasAmount = false;
        boolean hasDescription = false;

        for (String[] pair : args) {
            if (pair[0].isEmpty()) {
                System.err.println("Syntax Error: Empty argument");
                return false;
            }

            if (pair[0].equals("--id")) {
                if (hasId) {
                    System.err.println("Error: Multiple '--id' arguments found.");
                    return false;
                }
                hasId = true;
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
            } else if (pair[0].equals("--amount")) {
                if (hasAmount) {
                    System.err.println("Error: Multiple '--amount' arguments found.");
                    return false;
                }
                hasAmount = true;
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
            } else if (pair[0].equals("--description")) {
                if (hasDescription) {
                    System.err.println("Error: Multiple '--description' arguments found.");
                    return false;
                }
                hasDescription = true;
            } else {
                System.err.println("Invalid argument: " + pair[0]);
                return false;
            }
        }

        // Check if all three argument types are present exactly once
        if (hasId && hasAmount && hasDescription) {
            return true;
        } else {
            System.err.println(
                    "Error: Missing argument(s).  Please provide all three arguments: --id, --amount, and --description.");
            return false;
        }
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
