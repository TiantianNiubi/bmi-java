import java.util.Scanner;

public class ATMSimulator {
    public static void main(String[] args) {
        // Initialize account balance (starting with 1000 units)
        double balance = 1000.0;
        // Create Scanner object for console input
        Scanner scanner = new Scanner(System.in);
        int choice; // Store user's menu selection

        // Main loop: runs until user selects 0 (Exit)
        do {
            // Display ATM menu
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw money");
            System.out.println("3. Deposit money");
            System.out.println("0. Exit");
            System.out.print("Choose action: ");

            // Get user menu choice
            choice = scanner.nextInt();

            // Handle menu selection with switch-case
            switch (choice) {
                case 1:
                    // Check balance
                    System.out.printf("Your current balance: %.1f units%n", balance);
                    break;

                case 2:
                    // Withdraw money
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();

                    // Input validation: positive amount + multiple of 100
                    if (withdrawAmount <= 0) {
                        System.out.println("Error: Please enter a positive amount!");
                    } else if (withdrawAmount % 100 != 0) {
                        System.out.println("Error: Amount must be a multiple of 100!");
                    } else if (withdrawAmount > balance) {
                        // Insufficient funds check
                        System.out.printf("Error: insufficient funds. Available: %.1f%n", balance);
                    } else {
                        // Valid withdrawal: update balance
                        balance -= withdrawAmount;
                        System.out.printf("Success! You withdrew: %.1f units%n", withdrawAmount);
                        System.out.printf("New balance: %.1f units%n", balance);
                    }
                    break;

                case 3:
                    // Deposit money
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();

                    // Input validation: positive amount + multiple of 100
                    if (depositAmount <= 0) {
                        System.out.println("Error: Please enter a positive amount!");
                    } else if (depositAmount % 100 != 0) {
                        System.out.println("Error: Amount must be a multiple of 100!");
                    } else {
                        // Valid deposit: update balance
                        balance += depositAmount;
                        System.out.printf("Success! You deposited: %.1f units%n", depositAmount);
                        System.out.printf("New balance: %.1f units%n", balance);
                    }
                    break;

                case 0:
                    // Exit program
                    System.out.println("Thank you for using the ATM! Goodbye!");
                    break;

                default:
                    // Invalid menu choice
                    System.out.println("Invalid choice! Please select 0-3.");
            }

        } while (choice != 0); // Loop continues until choice is 0

        // Close the scanner to prevent resource leak
        scanner.close();
    }
}