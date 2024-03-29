/*
Project 1
Abstract: This program consists of a banking system that is designed to
manage customer and account data. The Bank class reads customer and account
data from a file, create new customers and accounts, perform transactions, and
provide information about the bank's status. The Account class manages bank
accounts, allowing deposits and withdrawals. The Customer class represents bank
customers, with methods for printing customer information and extracting the last
four digits of the SSN. The Transaction class records transaction details.
Rayan Hamid
26 September, 2023
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Bank {

    private String bankName;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    // Method that reads data from a file
    public void readData(String file) {
        Scanner inputStream;
        try {
            inputStream = new Scanner(new FileInputStream(file));
            //reads the number of customers and their information
            int numCustomers = inputStream.nextInt();
            inputStream.nextLine();
            for (int i = 0; i < numCustomers; i++) {
                String customerLine = inputStream.nextLine();
                String[] customerInformation = customerLine.split(",");
                String name = customerInformation[0];
                String address = customerInformation[1];
                int zipCode = Integer.parseInt(customerInformation[2]);
                String ssn = customerInformation[3];
                Customer customer = new Customer(name, address, zipCode, ssn);
                customers.add(customer);
            }
            //reads the number of accounts and their information
            int numAccounts = inputStream.nextInt();
            inputStream.nextLine();
            for (int i = 0; i < numAccounts; i++) {
                String accountLine = inputStream.nextLine();
                String[] accountInformation = accountLine.split(",");
                String ssn = accountInformation[0];
                int accountNumber = Integer.parseInt(accountInformation[1]);
                int accountType = Integer.parseInt(accountInformation[2]);
                try {
                    double currentBalance = Double.parseDouble(accountInformation[3]);
                    Account account = new Account(ssn, accountNumber, accountType, currentBalance);
                    accounts.add(account);
                } catch (NumberFormatException e) {

                }
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found or could not be opened.");
            System.exit(0);
        }
    }

    // Method that creates a new customer
    public void newCustomer(String name, String address, int zipCode, String ssn) {
        Customer customer = findCustSsn(ssn);
        if (customer != null) {
            System.out.println(name + " is not added - Existing customer with matching SSN in system.");
        } else {
            customer = new Customer(name, address, zipCode, ssn);
            customers.add(customer);
            System.out.println(name + " is added.");
        }
    }

    //method that removes an account
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    // Method that adds a new account
    public void newAccount(String ssn, int accountNumber, int accountType, double balance) {
        boolean accountAlreadyExists = false;

        // Check to see if the account number already exists
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                accountAlreadyExists = true;
                System.out.println("Account creation failed - Account " + accountNumber + " already exists");
                return;
            }
        }

        int count = 0;
        // Count the existing accounts for the SSN
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getSsn().equals(ssn)) {
                count++;
                if (count >= 2) {
                    System.out.println("Account creation failed - Customer " + findCustSsn(ssn).getName() + " already has two accounts");
                    return;
                }
            }
        }
        // If the account doesn't exist and the customer can have more accounts, create it
        accounts.add(new Account(ssn, accountNumber, accountType, balance));
        System.out.println("Account creation - Number: " + accountNumber + ", Customer: " + findCustSsn(ssn).getName());
    }

    // Method that prints bank info
    public void bankInfo() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Number of Customers: " + customers.size());
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println(customer.getName() + ": " + customer.getSsn());
        }
        System.out.println("Number of Accounts: " + accounts.size());
        double totalBalance = 0;
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.printf("  %d: $%.2f%n", account.getAccountNumber(), account.getCurrentBalance());
            totalBalance += account.getCurrentBalance();
        }
        System.out.printf("Total Balance: $%.2f%n", totalBalance);
    }

    // Method that prints account info
    public void accountInfo(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("- Number: " + account.getAccountNumber());
                String accountType;
                if (account.getAccountType() == 1) {
                    accountType = "Checking";
                } else {
                    accountType = "Savings";
                }
                System.out.println("- " + accountType);
                System.out.printf("- Balance: $%.2f%n", account.getCurrentBalance());
                Customer customer = findCustSsn(account.getSsn());
                System.out.println("- Customer: " + customer.getName());
                return;
            }
        }
        System.out.println("Account (" + accountNumber + ") does not exist.");
    }

    // Method that deposits amount
    public void deposit(int accountNumber, double amount) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                account.deposit(amount);
                Transaction transaction = new Transaction(accountNumber, 1, amount);
                transactions.add(transaction);
                return;
            }
        }
        System.out.println("Account (" + accountNumber + ") does not exist.");
    }

    // Method that withdraws amount
    public void withdraw(int accountNumber, double amount) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                if (account.withdraw(amount)) {
                    Transaction transaction = new Transaction(accountNumber, 2, amount);
                    transactions.add(transaction);
                    return;
                }
            }
        }
        System.out.println("Account (" + accountNumber + ") does not exist or insufficient balance.");
    }


    // Method that closes account
    public boolean closeAccount(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                accounts.remove(account);
                return true;
            }
        }
        return false;
    }

    // Method that prints transaction for an account
    public void transaction(int accountNumber) {
        boolean Transaction = false;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAccountNumber() == accountNumber) {
                Transaction = true;
                System.out.println("- " + transaction);
            }
        }
        if (!Transaction) {
            System.out.printf("  - No transaction for account %d%n", accountNumber);
        }
    }

    //method that retrieves customer info with last four of ssn
    public void customerInfoWithSSN(int lastFour) {
        boolean found = false;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getLastFour() == lastFour) {
                found = true;
                customer.printCustomerInfo();
            }
        }
        if (!found) {
            System.out.println("No customer with " + lastFour);
        }
    }

    // Method that removes a customer
    public void removeCustomer(String ssn) {
        Customer removeCust = findCustSsn(ssn);
        if (removeCust != null) {
            ArrayList<Account> customerAccounts = new ArrayList<>();
            for (int i = 0; i < accounts.size(); i++) {
                Account account = accounts.get(i);
                if (account.getSsn().equals(ssn)) {
                    customerAccounts.add(account);
                }
            }
            for (int i = 0; i < customerAccounts.size(); i++) {
                Account account = customerAccounts.get(i);
                boolean close = closeAccount(account.getAccountNumber());
                if (close) {
                    System.out.println("Account closed - Number: " + account.getAccountNumber() + " $" + account.getCurrentBalance());
                }
            }
            customers.remove(removeCust);
            System.out.println("Customer removed - SSN: " + ssn + ". Customer: " + removeCust.getName());
        } else {
            System.out.println("Customer remove failed. SSN does not exist.");
        }
    }


    // method that gets a customer by ssn
    public Customer findCustSsn(String ssn) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getSsn().equals(ssn)) {
                return customer;
            }
        }
        return null;
    }
}

