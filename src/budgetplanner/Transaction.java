package budgetplanner;

/**
 * Represents a single financial transaction (income or expense).
 */
public class Transaction {
    private String type; // "Income" or "Expense"
    private String description;
    private double amount;

    /**
     * Constructs a new Transaction.
     * @param type the type of transaction ("Income" or "Expense")
     * @param description what the transaction was for
     * @param amount how much money was involved
     */
    public Transaction(String type, String description, double amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    public String getType() { return type; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return type + " - " + description + " : £" + String.format("%.2f", amount);
    }
}
