import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Transaction {
    //attributes
    private int accountNumber;
    private int transactionType;
    private String transactionDateTime;
    private double amount;
    //constructor
    public Transaction(int accountNumber, int transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    //getters
    public int getAccountNumber() {
        return accountNumber;
    }
    public int getTransactionType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }


    //toString method that formats the transaction details
    @Override
    public String toString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
        String formatDateTime = now.format(formatter);

        if (transactionType == 1) {
            return String.format("- Account Number: %d, Deposit ($%.2f), %s",
                    accountNumber, amount, transactionDateTime);
        } else if (transactionType == 2) {
            return String.format("- Account Number: %d, Withdraw ($%.2f), %s",
                    accountNumber, amount, transactionDateTime);
        } else if (transactionType == 3) {
            return String.format("- Account Number: %d, Account closed, %s",
                    accountNumber, transactionDateTime);
        } else {
            return "Unknown Transaction Type";
        }    }


}