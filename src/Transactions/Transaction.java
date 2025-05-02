package Transactions;

import java.time.LocalDateTime; // Modern date-time API
import Accounts.Account;
import Exceptions.InsufficientFundsException;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private LocalDateTime timestamp; // Date of the transaction (use LocalDateTime for better handling)
    private final Account account; // Account on which the transaction is to be performed
    private final Account targetAccount; // For transfer, we need target account
    

    // Constructor that accepts the type, amount, date, and account, with an
    // optional targetAccount for transfers
    public Transaction(TransactionType type, double amount,  LocalDateTime timestamp,
            Account targetAccount, Account account) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.account = account;
        this.targetAccount = targetAccount; // For transfers, this is needed
    }

    // Method to execute the transaction (deposit, withdrawal, transfer)
    public void execute() {
        try {
            switch (type) {
                case DEPOSIT:
                    account.deposit(amount); // Perform deposit
                    break;
                case WITHDRAWAL:
                    // Check if the account has sufficient balance before withdrawing
                    if (account.getBalance() < amount) {
                        throw new InsufficientFundsException("Insufficient funds for withdrawal.");
                    }
                    account.withdraw(amount); // Perform withdrawal
                    break;
                case TRANSFER:
                    // Ensure targetAccount is provided for transfers
                    if (targetAccount == null) {
                        throw new IllegalArgumentException("Target account is not provided for transfer.");
                    }

                    // Check if the account has sufficient balance for transfer
                    if (account.getBalance() < amount) {
                        throw new InsufficientFundsException("Insufficient funds for transfer.");
                    }

                    // Perform transfer logic: Withdraw from source and deposit into target account
                    account.withdraw(amount); // Withdraw from the source account
                    targetAccount.deposit(amount); // Deposit into the target account
                    break;
                default:
                    throw new IllegalArgumentException("Invalid transaction type.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            // Optionally log the error, re-throw, or handle differently based on your needs
        }
    }

    // Getters
    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    // Getter and Setter for timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}