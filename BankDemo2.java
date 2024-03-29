public class BankDemo2 {
    public static void main(String[] args) {
        Bank csumbBank = new Bank("CSUMB");
        csumbBank.readData("resources/test1.txt");
        System.out.println("=== NEW CUSTOMERS ===");
        csumbBank.newCustomer("Bob Smith", "123 University Center", 93955, "123-45-6789");
        csumbBank.newCustomer("Unknown Smith", "123 University Center", 93955, "777-77-7777");
        System.out.println("\n=== NEW ACCOUNTS ===");
        csumbBank.newAccount("777-77-7777", 4000, 1, 100.50);
        csumbBank.newAccount("123-45-7777", 2000, 1, 100.50);
        csumbBank.newAccount("123-45-7777", 4000, 1, 100.50);
        System.out.println("\n=== ACCOUNT INFO ===");
        csumbBank.accountInfo(7000);
        System.out.println("\n=== CUSTOMER WITH LAST FOUR DIGIT SSN 7979===");
        csumbBank.customerInfoWithSSN(7979);
        System.out.println("\n=== CUSTOMER WITH LAST FOUR DIGIT SSN 7777 ===");
        csumbBank.customerInfoWithSSN(7777);
        System.out.println("\n=== CUSTOMER WITH LAST FOUR DIGIT SSN 6789 ===");
        csumbBank.customerInfoWithSSN(6789);
        // We try to close the account 2000.
        csumbBank.closeAccount(2000);
        System.out.println("\n=== REMOVE CUSTOMER: 555-55-5555 ===");
        csumbBank.removeCustomer("555-55-5555");
        System.out.println("\n=== REMOVE CUSTOMER: 777-77-7777 ===");
        csumbBank.removeCustomer("777-77-7777");
    }
}
