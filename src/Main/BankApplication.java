package Main;

import java.util.Scanner;
import Accounts.Account;
import Accounts.CheckingAccount;
import Accounts.SavingsAccount;
import Accounts.BusinessAccount;
import Bank.Bank;
import Bank.BankService;
import Exceptions.AccountNotFoundException;
import Exceptions.InsufficientFundsException;

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
        System.out.print("Enter account type (Checking/Savings/Business): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter initial deposit: $");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline
        
        if (initialDeposit <= 0) {
            System.out.println("Initial deposit must be greater than zero.");
            return;
        }

        String accountNumber = "ACC" + System.currentTimeMillis(); // Unique account number generator
        Account newAccount = null;
        
        if ("CHECKING".equalsIgnoreCase(accountType)) {
            newAccount = new CheckingAccount(accountNumber, initialDeposit, 500.0);
        } else if ("SAVINGS".equalsIgnoreCase(accountType)) {
            System.out.print("Enter interest rate: ");
            double interestRate = scanner.nextDouble();
            newAccount = new SavingsAccount(accountNumber, initialDeposit, interestRate);
        } else if ("BUSINESS".equalsIgnoreCase(accountType)) {
            System.out.print("Enter business name: ");
            String businessName = scanner.nextLine();
            System.out.print("Enter business credit limit: $");
            double businessLimit = scanner.nextDouble();
            newAccount = new BusinessAccount(accountNumber, initialDeposit, businessLimit, businessName);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        bankService.addAccount(newAccount);
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + newAccount.getAccountNumber());
        System.out.println("Account Type: " + newAccount.getClass().getSimpleName());
        System.out.println("Initial Deposit: $" + initialDeposit);
    }
    
    private static void performDeposit(Scanner scanner, BankService bankService) throws AccountNotFoundException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        Account account = bankService.getAccount(accountNumber);
        account.deposit(amount);
        System.out.println("Deposit successful! New balance: $" + account.getBalance());
    }
    
    private static void performWithdraw(Scanner scanner, BankService bankService) throws AccountNotFoundException, InsufficientFundsException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        Account account = bankService.getAccount(accountNumber);
        account.withdraw(amount);
        System.out.println("Withdrawal successful! New balance: $" + account.getBalance());
    }
    
    private static void performCheckBalance(Scanner scanner, BankService bankService) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        try {
            Account account = bankService.getAccount(accountNumber);
            System.out.println("Account balance: $" + account.getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void performTransfer(Scanner scanner, BankService bankService) throws AccountNotFoundException, InsufficientFundsException {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        System.out.print("Enter destination account number: ");
        String destinationAccountNumber = scanner.nextLine();
        System.out.print("Enter transfer amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }

        Account sourceAccount = bankService.getAccount(sourceAccountNumber);
        Account destinationAccount = bankService.getAccount(destinationAccountNumber);

        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
            System.out.println("Transfer successful! $" + amount + " transferred from " 
                                + sourceAccountNumber + " to " + destinationAccountNumber);
        } else {
            System.out.println("Insufficient funds in source account.");
        }
    }    
}