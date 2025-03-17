abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    BankAccount(String accountNumber, String holderName, double balance) {
        setAccountNumber(accountNumber);
        setHolderName(holderName);
        setBalance(balance);
    }

    public void accountDetails() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Holder Name: " + this.holderName);
        System.out.println("Balance: " + this.balance);
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds");
        }
    }

    abstract double calculateInterest();
}

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

class SavingsAccount extends BankAccount implements Loanable {
    private double interestRate = 0.04; // 4% interest
    private boolean loanEligible;

    SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println("Loan of " + amount + " approved for Savings Account");
        } else {
            System.out.println("Loan request denied for Savings Account");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        loanEligible = getBalance() >= 5000;
        return loanEligible;
    }
}

class CurrentAccount extends BankAccount implements Loanable {
    private double interestRate = 0.02; // 2% interest
    private boolean loanEligible;

    CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println("Loan of " + amount + " approved for Current Account");
        } else {
            System.out.println("Loan request denied for Current Account");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        loanEligible = getBalance() >= 10000;
        return loanEligible;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount("SA123", "John Doe", 6000);
        BankAccount current = new CurrentAccount("CA456", "Jane Smith", 12000);

        BankAccount[] accounts = {savings, current};

        for (BankAccount account : accounts) {
            account.accountDetails();
            System.out.println("Interest Earned: " + account.calculateInterest());
            ((Loanable) account).applyForLoan(5000);
            System.out.println("--------------");
        }
    }

//    Account Number: SA123
//    Holder Name: John Doe
//    Balance: 6000.0
//    Interest Earned: 240.0
//    Loan of 5000.0 approved for Savings Account
//    --------------
//    Account Number: CA456
//    Holder Name: Jane Smith
//    Balance: 12000.0
//    Interest Earned: 240.0
//    Loan of 5000.0 approved for Current Account
//    --------------
}
