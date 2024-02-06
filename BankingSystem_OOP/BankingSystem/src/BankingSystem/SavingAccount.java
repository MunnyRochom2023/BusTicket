package BankingSystem;

public class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        Super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    @Override
    public void withdraw(double withdrawMoney) {
        double penalty = 0.02; 

        if (withdrawMoney > getBalance()) {
            System.out.println("Withdrawal amount exceeds account balance. A penalty of $" + penalty + " will be applied.");
            uper.withdraw(withdrawMoney + penalty);
        } else {
            super.withdraw(withdrawMoney);
        }
    }
}
