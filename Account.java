public class Account {
   //attributes
    private String ssn;
    private int accountNumber;
    private int accountType;
    private double currentBalance;

    //constructor
    public Account(String ssn, int accountNumber, int accountType, double currentBalance) {
        this.ssn = ssn;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
    }

    //getters
    public String getSsn() {
        return ssn;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAccountType() {
        return accountType;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    //method that deposits a specified amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            currentBalance += amount;
        }
    }

    //method that withdraws a specified amount from the account
    //returns true if withdrawal is successful, returns false otherwise
    public boolean withdraw(double amount) {
        if (amount > 0 && currentBalance >= amount) {
            currentBalance -= amount;
            return true;
        }
        return false;
    }



}

