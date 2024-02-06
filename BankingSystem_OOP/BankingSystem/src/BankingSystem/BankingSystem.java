

package BankingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {

    private static final String USER_INFO_FILE = "Userinfo.txt";
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Account");
            System.out.println("3. Savings Account");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    accountMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter AccountNumber: ");
        String accountNumber = scanner.next();
        System.out.print("Enter AccountHolder: ");
        String accountHolder = scanner.next();
        System.out.print("Enter PIN number (Digits only): ");
        String pin = scanner.next();
    
        double balance = 0.0;  // Initial balance
        Account account = new Account(accountNumber, accountHolder, balance);
        accounts.put(accountNumber, account);
    
        String userInfo = accountNumber + "/" + accountHolder + "/" + pin;
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_INFO_FILE, true))) {
            writer.println(userInfo);
            System.out.println("Account has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void accountMenu(Scanner scanner) {
        System.out.print("Enter AccountNumber: ");
        String accountNumber = scanner.next();
        System.out.print("Enter AccountHolder: ");
        String accountHolder = scanner.next();
        System.out.print("Enter PIN number (Digits only): ");
        String pin = scanner.next();

        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found. Please create an account.");
            createAccount(scanner);
        } else {
            Account account = accounts.get(accountNumber);
            viewAccount(account, scanner);
        }
    }

    private static void viewAccount(Account account, Scanner scanner) {
        while (true) {
            System.out.println("Account Menu:");
            System.out.println("a. Set Balance");
            System.out.println("b. Deposit Money");
            System.out.println("c. Withdraw Money");
            System.out.println("d. Saving Account");

            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'a':
                    setBalance(account, scanner);
                    break;
                case 'b':
                    depositMoney(account, scanner);
                    break;
                case 'c':
                    withdrawMoney(account, scanner);
                    break;
                case 'd':
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void setBalance(Account account, Scanner scanner) {
        System.out.println("Enter the new balance: ");
        double newBalance = scanner.nextDouble();
        account.setBalance(newBalance);
        System.out.println("Balance set successfully.");
        displayAccountDetails(account);
    }

    private static void depositMoney(Account account, Scanner scanner) {
        System.out.println("Enter the amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
        System.out.println("Deposit successful.");
        displayAccountDetails(account);
    }

    private static void withdrawMoney(Account account, Scanner scanner) {
        System.out.println("Enter the amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);
        System.out.println("Withdrawal successful.");
        displayAccountDetails(account);
    }

    private static void displayAccountDetails(Account account) {
        System.out.println("AccountNumber: " + account.getAccountNumber());
        System.out.println("AccountHolder: " + account.getAccountHolder());
        System.out.println("Balance: $" + account.getBalance());
    }

}
