package Accounts;

import java.util.Random;

public final class Customer {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String accountNumber;
    private Account bankAccount; // Reference to an Account object

    // Constructor to initialize the customer
    public Customer(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        System.out.println("Initializing customer: " + name + ", " + email);
        this.accountNumber = generateAccountNumber(); // Generate unique account number
        System.out.println("Generated Account Number: " + this.accountNumber);
    }

    // Constructor with name only (for simplicity)
    public Customer(String name) {
        this(name, "Unknown Address", "Unknown Phone", "Unknown Email");
    }

    // Constructor with address, email, and phone number (for simpler customer
    // creation)
    public Customer(String address, String email, String phoneNumber) {
        this("Unknown Name", address, phoneNumber, email); // Default to unknown name if not provided
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Account getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Method to generate a random account number
    public String generateAccountNumber() {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong() % 10000000000L); // 10 digits
        return "AC" + String.format("%010d", randomNumber); // Prefix "AC" to make it clear this is an account
    }

    // Display customer details
    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Account Number: " + accountNumber);
        if (bankAccount != null) {
            System.out.println("Acount Type: " + bankAccount.getAccountType());
            System.out.println("Balance: " + bankAccount.getBalance());
        }
    }
}
