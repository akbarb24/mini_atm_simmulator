package com.mitrais.atm.screen;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.service.AccountService;
import com.mitrais.atm.service.TransactionService;
import com.mitrais.atm.validation.TransferValidation;

import java.util.Random;
import java.util.Scanner;

public class TransferScreen {
    private static TransferScreen INIT;

    TransactionScreen transactionScreen = TransactionScreen.getInit();
    Scanner scanner = new Scanner(System.in);

    public static TransferScreen getInit() {
        if (INIT == null)
            INIT = new TransferScreen();
        return INIT;
    }

    public void show(Account account) {
        String accountNumber;
        String amountStr;
        String refNumber;

        do {
            //show screen1 - getting account number destination
            accountNumber = getAccountNumber();
            if (accountNumber.isEmpty())
                transactionScreen.show(account);

            //show screen2 - getting amount to transfer
            amountStr = getAmount();
            if (amountStr.isEmpty())
                transactionScreen.show(account);

        } while (!TransferValidation.validateAccountNumber(accountNumber)
                || !TransferValidation.validateAmount(amountStr, account));

        //show screen3 - get reference number
        refNumber = getRefNumber();

        //show screen4 - confirmation
        if (!confirm(accountNumber, amountStr, refNumber)) {
            transactionScreen.show(account);
        }
        transferProcess(account, accountNumber, amountStr, refNumber);
    }

    private boolean confirm(String accountNumber, String amountStr, String refNumber) {
        System.out.print("----------------------------\n" +
                "Transfer Confirmation\n" +
                "Destination Account : " + accountNumber + "\n" +
                "Transfer Amount     : $" + amountStr + "\n" +
                "Reference Number    : " + refNumber + "\n" +
                "\n" +
                "1. Confirm Trx\n" +
                "2. Cancel Trx\n" +
                "Choose option[2]:");
        String input = scanner.nextLine();
        if (input.equals("1"))
            return true;
        return false;
    }

    private void transferProcess(Account account, String destAccountNumber, String amountStr, String refNumber) {
        TransactionService transactionService = new TransactionService(account);
        AccountService accountService = AccountService.getInit();

        Account destAccount = accountService.findByAccountNumber(destAccountNumber);
        Integer amount = Integer.valueOf(amountStr);

        Account latestAccount = transactionService.transfer(destAccount, amount);
        showSummary(latestAccount, destAccountNumber, amount, refNumber);
    }

    private void showSummary(Account account, String destAccountNumber, Integer amount, String refNumber) {
        System.out.print("----------------------------\n" +
                "Fund Transfer Summary\n" +
                "Destination Account : " + destAccountNumber + "\n" +
                "Transfer Amount     : $" + amount + "\n" +
                "Reference Number    : " + refNumber + "\n" +
                "Balance             : $" + account.getBalance() + "\n" +
                "\n" +
                "1. Transaction\n" +
                "2. Exit\n" +
                "Choose option[2]:");
        String input = scanner.nextLine();
        if (input.equals("1"))
            transactionScreen.show(account);
        else {
            WelcomeScreen.getInit().show();
        }
    }

    private String getAccountNumber() {
        System.out.println("----------------------------\n" +
                "Please enter destination account and press enter to continue \n" +
                "or keep it blank and press enter to go back to Transaction: ");
        return scanner.nextLine();
    }

    private String getAmount() {
        System.out.println("----------------------------\n" +
                "Please enter transfer amount and press enter to continue or \n" +
                "keep it blank and press enter to go back to Transaction: ");
        System.out.print("$");
        return scanner.nextLine();
    }

    private String getRefNumber() {
        Random rnd = new Random();
        Integer number = rnd.nextInt(999999);

        System.out.format("----------------------------\n" +
                "Reference Number: %06d\n" +
                "press enter to continue.", number);

        scanner.nextLine();
        return String.valueOf(number);
    }
}
