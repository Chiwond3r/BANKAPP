package Accounts;

import java.time.LocalDateTime;
import java.util.ArrayList; // Import ArrayList
import java.util.HashMap;
import java.util.List; // Import List
import java.util.Map;
import Exceptions.AccountNotFoundException;
import Exceptions.InsufficientFundsException;
import Transactions.Transaction;
import Transactions.TransactionType;

public class Account {
    private final String accountNumber;
    private double balance; // Allow balance modification
    private final List<Transaction> transactionHistory; // List to track transactions
    // ✅ No-arg constructor

    public Account() {
        this.balance = 0.0;
        this.accountNumber = "";
        this.transactionHistory = null;
    }

    // Constructor for Account
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>(); // Initialize the transaction history
    }

    // Assuming you have a Map to store accounts in your Bank class or somewhere in
    // your code
    private final Map<String, Account> accounts = new HashMap<>();

    // Getter for account number
    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        // Use the map to retrieve the account by accountNumber
        Account account = accounts.get(accountNumber); // Correct method to access the account by accountNumber
        if (account == null) {
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
        }
        return account;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber; // Return the account number
    }

    // Default account type (can be overridden by subclasses)
    public String getAccountType() {
        return "General Account";
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: $" + balance;
    }

    // Deposit method to add money to the balance
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Add the deposit amount to the account balance

            // Create a Transaction object to track the deposit
            Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now(), this, null);
            transactionHistory.add(transaction); // Add the transaction to the account's transaction history

            // Log the deposit and print it
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive."); // Handle invalid deposit amounts
        }
    }

    // Method to display the current balance
    public void displayBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount; // Subtract the amount from the balance

                // Create a Transaction object to track the withdrawal
                Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, LocalDateTime.now(), this,
                        null);
                transactionHistory.add(transaction); // Add the transaction to the account's transaction history

                // Log the withdrawal and print it
                System.out.println("Withdrew: " + amount + " | New Balance: " + balance);
            } else {
                System.out.println("Insufficient balance for withdrawal."); // Insufficient funds check
            }
        } else {
            System.out.println("Withdrawal amount must be positive."); // Handle invalid withdrawal amounts
        }
    }

    public void transfer(Account destinationAccount, double amount) throws InsufficientFundsException {
        if (amount > 0) {
            if (amount <= balance) {
                // Withdraw the amount from the current account
                withdraw(amount);

                // Deposit the amount into the destination account
                destinationAccount.deposit(amount);

                // Create a Transaction object to track the transfer
                Transaction transaction = new Transaction(
                        TransactionType.TRANSFER,
                        amount,
                        LocalDateTime.now(),
                        this,
                        destinationAccount // Include destination account for transfer
                );

                // Add the transaction to the transaction history
                transactionHistory.add(transaction);

                // Log the transfer
                System.out.println("Transferred: " + amount + " to Account: " + destinationAccount.getAccountNumber());
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }

    // Getter for transaction history
    public List<Transaction> getTransactionHistory() {
        return transactionHistory; // Return the transaction history list
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

}
