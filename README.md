# BANK APP
A simple banking application that allows users to manage accounts, make deposits, withdrawals, transfers, and track transaction history.

## Features
- **Account Creation**: Create accounts with unique account numbers and balances.
- **Deposit**: Deposit funds into an account.
- **Withdrawal**: Withdraw funds from an account with sufficient balance.
- **Transfer**: Transfer funds between accounts.
- **Transaction History**: Track deposits, withdrawals, and transfers with timestamps.
- **Account Management**: View account balances and details.
- **Custom Exception Handling**: Includes `AccountNotFoundException` and `InsufficientFundsException`.

## Technologies Used
- **Java**
- **Java 8+** (LocalDateTime, Collections)

## Usage
- **Run the Application**: Compile and execute the `BankApplication` class:
  ```bash
  javac BankApplication.java
  java BankApplication
  ```
- **Deposit**: Call the `deposit(amount)` method on an account object.
- **Withdraw**: Call the `withdraw(amount)` method on an account object (throws `InsufficientFundsException` if balance is insufficient).
- **Transfer**: Call `transfer(destinationAccount, amount)` to transfer funds between accounts (throws `InsufficientFundsException` if balance is insufficient).
- **Transaction History**: Use `getTransactionHistory()` to view the list of transactions for an account.

## Exceptions
- **AccountNotFoundException**: Thrown when trying to access a non-existent account.
- **InsufficientFundsException**: Thrown when attempting to withdraw or transfer more money than available in the account.