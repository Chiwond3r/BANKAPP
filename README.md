#BANK APP
A simple banking application that allows users to manage accounts, make deposits, withdrawals, transfers, and track transaction history.

#FEATURES
ACCOUNT CREATION: Create accounts with unique account numbers and balances.
DEPOSIT: Deposit funds into an account.
WITHDRAWAL: Withdraw funds from an account with sufficient balance.
TRANSFER: Transfer funds between accounts.
TRANSACTION HISTORY: Track deposits, withdrawals, and transfers with timestamps.
ACCOUNT MANAGEMENT: View account balances and details.
CUSTOM EXCEPTION HANDLING (AccountNotFoundException, InsufficientFundsException)

#TECHNOLOGIES USED
Java
Java 8+ (LocalDateTime, Collections)

#USAGE
DEPOSIT: Call the deposit(amount) method on an account object.
WITHDRAW: Call the withdraw(amount) method on an account object (throws InsufficientFundsException if balance is insufficient).
TRANSFER: Call transfer(destinationAccount, amount) to transfer funds between accounts (throws InsufficientFundsException if balance is insufficient).
TRANSACTION HISTORY: Use getTransactionHistory() to view the list of transactions for an account.

#EXCEPTIONS
AccountNotFoundException: Thrown when trying to access a non-existent account.
InsufficientFundsException: Thrown when attempting to withdraw or transfer more money than available in the account.