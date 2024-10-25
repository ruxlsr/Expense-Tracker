# Expense-Tracker

Hi. this is a CLI app to track Exepenses exercise provided by [rodmap.sh](https://roadmap.sh/projects/expense-tracker);

# Features

- add an expense with a description and amount.
- update an expense.
- delete an expense.
- view all expenses.
- view a summary of all expenses.
- Users can view a summary of expenses for a specific month (of current year).

# installation

1. Clone this repository

```bash
git clone https://github.com/ruxlsr/Expense-Tracker.git
cd Expense-Tracker/src
```

2. Execute

```bash
# the jar file was exported with openjdk 21.0.5-ea 2024-10-15
java -jar Expense-tracker.jar <command>
```

You can also compile and execute whith your favorite IDE

```bash
# Usage
$ java -jar expense-tracker add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

$ java -jar expense-tracker add --description "Dinner" --amount 10
# Expense added successfully (ID: 2)

$ java -jar expense-tracker list
# Expenses-Tracker
# id    Date       Description          amount
# 1     2023-05-26 buy grocery          $200
# 4     2023-10-29 Gas refill           $40

$ java -jar expense-tracker summary
# Total expenses: $30

$ java -jar expense-tracker delete --id 1
# Expense deleted successfully

$ java -jar expense-tracker summary
# Total expenses: $20

$ expense-tracker summary --month 8
# Total expenses for August: $20
```
