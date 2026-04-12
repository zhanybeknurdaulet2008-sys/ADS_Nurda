package com.example.assigment1ads;

import java.util.*;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("Account: " + accountNumber +
                " | Username: " + username +
                " | Balance: " + balance);
    }
}

public class Assigment1AdsApplication {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> transactionHistory = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    static BankAccount[] predefinedAccounts = {
            new BankAccount("1001", "Ali", 150000),
            new BankAccount("1002", "Sara", 220000),
            new BankAccount("1003", "John", 300000)
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        accounts.addAll(Arrays.asList(predefinedAccounts));

        while (true) {
            System.out.println("\n=== MINI BANKING SYSTEM ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> bankMenu(sc);
                case 2 -> atmMenu(sc);
                case 3 -> adminMenu(sc);
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void bankMenu(Scanner sc) {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1. Submit Account Opening Request");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Add Bill Payment");
        System.out.println("5. Display All Accounts");
        System.out.println("6. Search Account by Username");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter account number: ");
                String accNum = sc.nextLine();
                System.out.print("Enter username: ");
                String username = sc.nextLine();

                accountRequests.add(new BankAccount(accNum, username, 0));
                System.out.println("Request submitted successfully!");
            }

            case 2 -> {
                BankAccount acc = findAccount(sc);
                if (acc != null) {
                    System.out.print("Deposit amount: ");
                    double amount = sc.nextDouble();
                    acc.deposit(amount);

                    transactionHistory.push("Deposit " + amount + " to " + acc.username);
                    System.out.println("Deposit successful!");
                }
            }

            case 3 -> {
                BankAccount acc = findAccount(sc);
                if (acc != null) {
                    System.out.print("Withdraw amount: ");
                    double amount = sc.nextDouble();

                    if (acc.withdraw(amount)) {
                        transactionHistory.push("Withdraw " + amount + " from " + acc.username);
                        System.out.println("Withdraw successful!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                }
            }

            case 4 -> {
                System.out.print("Enter bill name: ");
                String bill = sc.nextLine();
                billQueue.add(bill);

                transactionHistory.push("Bill Payment Added: " + bill);
                System.out.println("Bill added to queue!");
            }

            case 5 -> displayAllAccounts();

            case 6 -> {
                BankAccount acc = findAccount(sc);
                if (acc != null) {
                    acc.display();
                }
            }

            default -> System.out.println("Invalid option!");
        }
    }

    static void atmMenu(Scanner sc) {
        System.out.println("\n--- ATM MENU ---");
        System.out.println("1. Balance Enquiry");
        System.out.println("2. Withdraw");

        int choice = sc.nextInt();
        sc.nextLine();

        BankAccount acc = findAccount(sc);

        if (acc == null) return;

        switch (choice) {
            case 1 -> System.out.println("Current Balance: " + acc.balance);

            case 2 -> {
                System.out.print("Withdraw amount: ");
                double amount = sc.nextDouble();

                if (acc.withdraw(amount)) {
                    transactionHistory.push("ATM Withdraw " + amount + " from " + acc.username);
                    System.out.println("ATM Withdraw Successful!");
                } else {
                    System.out.println("Insufficient funds!");
                }
            }

            default -> System.out.println("Invalid ATM Option!");
        }
    }

    static void adminMenu(Scanner sc) {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. View Pending Account Requests");
        System.out.println("2. Process Next Account Request");
        System.out.println("3. View Bill Payment Queue");
        System.out.println("4. Process Next Bill Payment");
        System.out.println("5. View Last Transaction");
        System.out.println("6. Undo Last Transaction");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> {
                for (BankAccount acc : accountRequests) {
                    acc.display();
                }
            }

            case 2 -> {
                if (!accountRequests.isEmpty()) {
                    BankAccount newAcc = accountRequests.poll();
                    accounts.add(newAcc);

                    System.out.println("Account request processed:");
                    newAcc.display();
                } else {
                    System.out.println("No pending requests.");
                }
            }

            case 3 -> {
                System.out.println("Bill Queue:");
                for (String bill : billQueue) {
                    System.out.println(bill);
                }
            }

            case 4 -> {
                if (!billQueue.isEmpty()) {
                    System.out.println("Processing Bill: " + billQueue.poll());
                } else {
                    System.out.println("No bills in queue.");
                }
            }

            case 5 -> {
                if (!transactionHistory.isEmpty()) {
                    System.out.println("Last Transaction: " + transactionHistory.peek());
                } else {
                    System.out.println("No transactions yet.");
                }
            }

            case 6 -> {
                if (!transactionHistory.isEmpty()) {
                    System.out.println("Undo Transaction: " + transactionHistory.pop());
                } else {
                    System.out.println("No transaction to undo.");
                }
            }

            default -> System.out.println("Invalid Admin Option!");
        }
    }

    static BankAccount findAccount(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                return acc;
            }
        }

        System.out.println("Account not found!");
        return null;
    }

    static void displayAllAccounts() {
        System.out.println("\n--- ALL ACCOUNTS ---");

        for (BankAccount acc : accounts) {
            acc.display();
        }
    }
}