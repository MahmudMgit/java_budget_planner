package budgetplanner;

import java.io.*;
import java.util.*;

/**
 * Handles all logic for managing transactions and calculating balance.
 */
public class Budget {
    private List<Transaction> transactions;

    public Budget() {
        transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction (either income or expense).
     * @param type "Income" or "Expense"
     * @param description the purpose or source
     * @param amount the monetary value
     */
    public void addTransaction(String type, String description, double amount) {
        transactions.add(new Transaction(type, description, amount));
    }

    /**
     * Calculates the current balance based on all transactions.
     * @return the total balance
     */
    public double getBalance() {
        double balance = 0.0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("Income")) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    /**
     * Displays all transactions.
     */
    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.println("---- Transaction History ----");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("-----------------------------");
    }

    /**
     * Saves all transactions to a text file.
     */
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.println(t.getType() + "," + t.getDescription() + "," + t.getAmount());
            }
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads transactions from a text file.
     */
    public void loadFromFile(String filename) {
        transactions.clear();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 3) {
                    addTransaction(parts[0], parts[1], Double.parseDouble(parts[2]));
                }
            }
            System.out.println("Data loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        }
    }
}
