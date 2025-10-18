package budgetplanner;

import java.io.*;
import java.util.*;

/**
 * Handles saving and loading budget transactions to/from a text file.
 */
public class FileHandler {

    private static final String FILE_NAME = "budget_data.txt";

    /**
     * Saves the list of transactions to a file.
     *
     * @param transactions List of Transaction objects
     */
    public static void saveToFile(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.write(t.getType() + "," + t.getDescription() + "," + t.getAmount());
                writer.newLine();
            }
            System.out.println(" Data saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println(" Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads transactions from the file and returns them as a list.
     *
     * @return List of Transaction objects
     */
    public static List<Transaction> loadFromFile() {
        List<Transaction> transactions = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    transactions.add(new Transaction(description, amount, type));
                }
            }
            System.out.println(" Data loaded successfully from " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println(" No existing data found. A new file will be created on save.");
        }

        return transactions;
    }
}
