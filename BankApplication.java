package Main;

import Bank.Bank;
import Bank.BankService;
import Exceptions.AccountNotFoundException;
import Exceptions.InsufficientFundsException;
import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) throws AccountNotFoundException, InsufficientFundsException {
        // Initialize bank and bank service
        Bank bank = new Bank(); // Global bank instance
        BankService bankService = new BankService(bank.getAllAccounts()); // Ensure getAccounts() returns a valid map

        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                displayMenu();

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                switch (choice) {
                    case 1:
                        createAccount(scanner, bankService);
                        break;
                    case 2:
                        performDeposit(scanner, bankService);
                        break;
                    case 3:
                        performWithdraw(scanner, bankService);
                        break;
                    case 4:
                        performTransfer(scanner, bankService);
                        break;
                    case 5:
                        performCheckBalance(scanner, bankService);
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Bank Application ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Exit");
    }

    private static void createAccount(Scanner scanner, BankService bankService) {
        // ...existing code...
    }

    private static void performDeposit(Scanner scanner, BankService bankService) throws AccountNotFoundException {
        // ...existing code...
    }

    private static void performWithdraw(Scanner scanner, BankService bankService) throws AccountNotFoundException, InsufficientFundsException {
        // ...existing code...
    }

    private static void performCheckBalance(Scanner scanner, BankService bankService) {
        // ...existing code...
    }

    private static void performTransfer(Scanner scanner, BankService bankService) throws AccountNotFoundException, InsufficientFundsException {
        // ...existing code...
    }
}
