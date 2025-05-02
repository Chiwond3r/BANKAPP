package Accounts;

import Exceptions.InsufficientFundsException;

public class CheckingAccount extends Account {
    private final String accountNumber;
    private double balance;
    private double overdraftLimit; // Add overdraft limit field

    // Constructor for CheckingAccount class
    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit; // Set overdraft limit
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Getter for overdraftLimit
    public double overdraftLimit() {
        return overdraftLimit;
    }

    // Correct setter for overdraftLimit
    protected void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Setter for balance
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to get account type (Checking in this case)
    public String getAccountType() {
        return "Checking";
    }

    // Method to withdraw money, considering overdraft limit
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal. Overdraft limit exceeded.");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: $" + balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Successfully deposited: $" + amount);
    }
}