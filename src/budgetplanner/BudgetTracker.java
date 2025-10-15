package budgetplanner;

import java.util.Scanner;

/**
 * The main class that provides the menu and user interface.
 */
public class BudgetTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Budget budget = new Budget();
        budget.loadFromFile("budget_data.txt");

        while (true) {
            System.out.println("\n===== Budget Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Balance");
            System.out.println("5. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter income source: ");
                    String incomeDesc = input.nextLine();
                    System.out.print("Enter amount: £");
                    double incomeAmount = input.nextDouble();
                    budget.addTransaction("Income", incomeDesc, incomeAmount);
                    System.out.println("Income added.");
                    break;

                case 2:
                    System.out.print("Enter expense purpose: ");
                    String expenseDesc = input.nextLine();
                    System.out.print("Enter amount: £");
                    double expenseAmount = input.nextDouble();
                    budget.addTransaction("Expense", expenseDesc, expenseAmount);
                    System.out.println("Expense added.");
                    break;

                case 3:
                    budget.viewTransactions();
                    break;

                case 4:
                    System.out.printf("Current balance: £%.2f\n", budget.getBalance());
                    break;

                case 5:
                    budget.saveToFile("budget_data.txt");
                    System.out.println("Exiting... Goodbye!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
