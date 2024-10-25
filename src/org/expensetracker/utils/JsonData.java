package org.expensetracker.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.expensetracker.model.Expense;
import org.expensetracker.model.ExpensesList;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonData {
    private static Path JSON_PATH = Path.of("./datas.json");

    /**
     * 
     * @return List<Expense>
     */
    public static List<Expense> getDataFromJsonFile() {
        List<Expense> expensesList = new ArrayList<>();

        try {
            JSONObject jsonDatasObject = loadJsonData();

            // get expenses
            JSONArray expensesJsonArray = jsonDatasObject.getJSONArray("expenses");

            for (int i = 0; i < expensesJsonArray.length(); i++) {
                JSONObject expense = expensesJsonArray.getJSONObject(i);
                String[] expenseDate = expense.getString("expenseDate").split("-");

                expensesList.add(
                        new Expense(expense.getInt("id"), expense.getString("description"), expense.getInt("amount"),
                                expenseDate));
            }

            return expensesList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static JSONObject loadJsonData() throws IOException {

        // existance verification of file
        if (!Files.exists(JSON_PATH)) {
            Files.createFile(JSON_PATH);
            Files.writeString(JSON_PATH, "{\"expenses\":[], \"category\":[]}");
        }
        // get categories and expenses
        return new JSONObject(Files.readString(JSON_PATH));

    }

    public static void saveInFileJsonData(ExpensesList expenses) throws IOException {
        JSONArray expenseJsonArray = new JSONArray(expenses.getExpenses());

        StringBuilder content = new StringBuilder();
        content.append("{");
        content.append("\"expenses\":");
        content.append(expenseJsonArray.toString() + ",");
        content.append("\"category\":[]}");
        System.out.println("toJsons: " + content.toString());

        Files.writeString(JSON_PATH, content.toString());
    }
}
