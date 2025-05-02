package Accounts;

public class BusinessAccount extends Account {
    private final double businessLimit;
    private final String businessName;

    // Constructor for BusinessAccount
    public BusinessAccount(String accountNumber, double balance, double businessLimit, String businessName) {
        super(accountNumber, balance);
        this.businessLimit = businessLimit;
        this.businessName = businessName;
    }

    @Override
    public String getAccountType() {
        return "Business"; // Return account type as "Business"
    }

    // Getter for business name
    public String getBusinessName() {
        return businessName;
    }

    // Getter for business limit
    public double getBusinessLimit() {
        return businessLimit;
    }

    @Override
    public String toString() {
        return super.toString() + ", Business Name: " + businessName + ", Business Limit: $" + businessLimit;
    }
}