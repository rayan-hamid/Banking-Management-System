import java.util.ArrayList;
public class Customer {
    //attributes
    private  String name;
    private  String address;
    private int zipCode;
    private String ssn;
    private ArrayList<Account> accounts= new ArrayList<>();
   //constructor
    public Customer(String name, String address, int zipCode, String ssn) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.ssn = ssn;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }
    public String getSsn () {
        return ssn;
    }

    //method that prints customer information
    public void printCustomerInfo() {
        System.out.println("Name: " + name);
        System.out.println(address + ", " + zipCode);
        System.out.println("SSN: " + ssn);

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println(account);
        }

        if (accounts.isEmpty()) {
            System.out.println("No accounts");
        }
    }

    //method that extracts nd returns the last four of the customer's ssn
    public int getLastFour() {
        if (ssn.length() == 11) {
            String digits = ssn.substring(7);
            try {
                return Integer.parseInt(digits);
            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }



}