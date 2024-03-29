public class BankDemo1 {
    public static void main(String[] args) {
        Bank csumbBank = new Bank("CSUMB");
        System.out.println("=== READ DATA ===");
        csumbBank.readData("resources/test1.txt");
        System.out.println("=== DONE ===");
        System.out.println("\n=== BANK INFORMATION ===");
        csumbBank.bankInfo();
        System.out.println("\n=== ACCOUNT INFORMATION ===");
        csumbBank.accountInfo(1000);
        csumbBank.deposit(1000, 150.25);
        System.out.println("\n=== ACCOUNT INFORMATION ===");
        csumbBank.accountInfo(1000);
        csumbBank.withdraw(1000, 100);
        System.out.println("\n=== ACCOUNT INFORMATION ===");
        csumbBank.accountInfo(1000);
        System.out.println("\n=== ACCOUNT CLOSE ===");
        if (csumbBank.closeAccount(1000)) {
            System.out.println("Account closed.");
        }
        System.out.println("\n=== TRANSACTION INFO ===");
        csumbBank.transaction(1000);
        System.out.println("\n=== TRANSACTION INFO ===");
        csumbBank.transaction(2000);
    }
}
