package Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Accounts.Account;
import Exceptions.AccountNotFoundException;

public class Bank {
    private final Map<String, Account> accounts; // Declare a Map for storing accounts

    // Constructor
    public Bank() {
        accounts = new HashMap<>(); // Initialize the map
    }

    public void addAccount(Account account) {
        // Add the account to the map using the account number as the key
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account added successfully: " + account.getAccountNumber());
    }

    public void removeAccount(String accountNumber) throws AccountNotFoundException {
        // Check if the account exists by calling findAccount
        Account account = findAccount(accountNumber);

        // Print some details about the account (use the account variable)
        System.out.println("Removing account: " + account.getAccountNumber() + ", Balance: " + account.getBalance());

        // Remove the account by the account number (key)
        accounts.remove(accountNumber); // Corrected: remove by key (account number)

        // Print success message
        System.out.println("Account removed successfully: " + accountNumber);
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts.values()) { // Iterate over the Account objects in the Map
            if (account.getAccountNumber().equals(accountNumber)) {
                return account; // Return the account if the account number matches
            }
        }
        throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
    }

    // Method to get an account by account number
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber); // Returns null if account not found
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values()); // Get values (accounts) and convert them into a List
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("--- All Accounts ---");
            for (Account account : accounts.values()) { // Iterate over the Account objects in the Map
                System.out.println(account); // Assumes Account class has a suitable toString() method
            }
        }
    }
    // Removed duplicate accounts field and associated methods to resolve conflicts.
}