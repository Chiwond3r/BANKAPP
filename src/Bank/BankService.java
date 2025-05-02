package Bank;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Accounts.Account;
import Exceptions.AccountNotFoundException;
import Exceptions.InsufficientFundsException;
import Transactions.Transaction;
import Transactions.TransactionType;

public class BankService {

    private final Map<String, Account> accounts; // Map to store accounts by their account numbers

    // Constructor to initialize account map
    public BankService(List<Account> list) {
        this.accounts = new HashMap<>(); // Initialize the accounts map
    }

    public BankService(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    // Deposit method to deposit funds into an account
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        // Validate the deposit amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        // Find the account by account number
        Account account = accounts.get(accountNumber);

        // If the account is not found, throw an exception
        if (account == null) {
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
        }

        // Perform the deposit on the account
        account.deposit(amount); // Assuming the Account class has a deposit method

        // Print a success message
        System.out.println("Deposit of $" + amount + " was successful for account: " + accountNumber);
    }

    // Method to withdraw money from an account
    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }

        // Find the account using the account number
        Account account = accounts.get(accountNumber);

        // If the account is not found, throw an exception
        if (account == null) {
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
        }

        // Log the withdrawal transaction for this account
        logTransaction(account, amount, TransactionType.WITHDRAWAL);
    }

    // Method to transfer money from one account to another
    public void transfer(String sourceAccountNumber, String destinationAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        // Find the source and destination accounts using the account numbers
        Account sourceAccount = accounts.get(sourceAccountNumber);
        Account destinationAccount = accounts.get(destinationAccountNumber);

        // If any of the accounts is not found, throw an exception
        if (sourceAccount == null) {
            throw new AccountNotFoundException("Source account not found.");
        }
        if (destinationAccount == null) {
            throw new AccountNotFoundException("Destination account not found.");
        }

        // Perform the transfer between the two accounts
        sourceAccount.transfer(destinationAccount, amount);

        // Log the transfer transaction for both the source and destination accounts
        logTransaction(sourceAccount, amount, TransactionType.TRANSFER);
        logTransaction(destinationAccount, amount, TransactionType.TRANSFER);
    }

    private void logTransaction(Account account, double amount, TransactionType type) {
        Account destinationAccount = null; // Default to null if no second account is involved

        // If it's a transfer, you will have a destinationAccount
        if (type == TransactionType.TRANSFER) {
            // Assuming `destinationAccount` is passed or available
            // Example: destinationAccount = some method to get the destination account;
        }

        // Now create the transaction with the correct constructor
        Transaction transaction = new Transaction(
                type,
                amount,
                LocalDateTime.now(),
                account,
                destinationAccount // Pass destinationAccount here for transfer or null otherwise
        );

        // Execute the transaction
        transaction.execute();

        // Optionally add the transaction to account history
        account.getTransactionHistory().add(transaction);

        // Print the transaction details (optional)
        System.out.println("Transaction logged for account " + account.getAccounts() + ": " + transaction);
    }

    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        Account account = accounts.get(accountNumber); // Try to retrieve the account by account number
        if (account == null) {
            // If the account is not found, throw an exception
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
        }
        return account; // Return the account if found
    }

    public void addAccount(Account newAccount) {
        if (newAccount != null) {
            // Store the account in the accounts map using the account number as the key
            accounts.put(newAccount.getAccountNumber(), newAccount);
            System.out.println("Account with number " + newAccount.getAccountNumber() + " added successfully.");
        } else {
            System.out.println("Cannot add a null account.");
        }
    }

}