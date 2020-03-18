package com.mitrais.atm.screen;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.model.Transaction;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransactionScreen {
    private static TransactionScreen INIT;
    private Scanner scanner = new Scanner(System.in);

    public static TransactionScreen getInit() {
        if (INIT == null)
            INIT = new TransactionScreen();

        return INIT;
    }

    public void show(Account account) {
        System.out.println("----------------------------");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Statement");
        System.out.println("4. Exit");
        System.out.print("Please choose option[4]: ");

        selectTransaction(scanner.nextLine(), account);
    }

    private void selectTransaction(String option, Account account) {
        WelcomeScreen welcomeScreen = WelcomeScreen.getInit();
        WithdrawScreen withdrawScreen = WithdrawScreen.getInit();
        TransferScreen transferScreen = TransferScreen.getInit();

        switch (option) {
            case "1":
                withdrawScreen.show(account);
                break;
            case "2":
                transferScreen.show(account);
                break;
            case "3":
                showStatement(account);
                break;
            case "4":
                welcomeScreen.show();
                break;
            default:
                show(account);
                break;
        }
    }

    private void showStatement(Account account) {
        List<Transaction> transactionList = account.getTransactionList();
        if(transactionList != null && !transactionList.isEmpty()) {
            List<Transaction> transactionListFiltered = transactionList.stream()
                    .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            showTransactionList(transactionListFiltered, account);
        } else {
            showNoTransaction(account);
        }
    }

    private void exitStatement(Account account) {
        System.out.println("----------------------------");
        System.out.print("Press any key to go back to the menu...");
        scanner.nextLine();
        show(account);
    }

    private void showAccountDetail(Account account) {
        System.out.println("----------------------------");
        System.out.println("Name: " + account.getName());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("----------------------------");
    }

    private void showTransactionList(List<Transaction> transactionList, Account account) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");

        showAccountDetail(account);
        System.out.println("LAST TRANSACTIONS LIST");
        transactionList.forEach(t -> {
            String dateStr = sdf.format(t.getTransactionDate());
            System.out.format("| Date: %s | Amount: $%d | Type: %s | Description: %s |%n",
                    dateStr, t.getAmount(), t.getTransactionType(), t.getDescription());
        });
        exitStatement(account);
    }

    private void showNoTransaction(Account account) {
        showAccountDetail(account);
        System.out.println(" NO TRANSACTION ");
        exitStatement(account);
    }
}
