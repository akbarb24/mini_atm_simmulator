package com.mitrais.atm.screen;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.service.TransactionService;
import com.mitrais.atm.validation.WithdrawValidation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WithdrawScreen {
    private static WithdrawScreen INIT;
    private Scanner scanner = new Scanner(System.in);

    public static WithdrawScreen getInit() {
        if (INIT == null)
            INIT = new WithdrawScreen();

        return INIT;
    }

    public void show(Account account) {
        System.out.println("----------------------------");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5] : ");

        selectAmount(scanner.next(), account);
    }

    private void showOtherAmount(Account account) {
        System.out.println("----------------------------");
        System.out.println("Other Withdraw");
        System.out.print("Enter amount to withdraw : ");

        String amount = scanner.next();
        withDrawProcess(amount, account);
    }

    private void showSummary(Account accountSummary, Integer amount) {
        String pattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());


        System.out.println("----------------------------");
        System.out.println("--------- Summary  ---------");
        System.out.println("Date        : " + date);
        System.out.println("Withdraw    : $" + amount);
        System.out.println("Balance     : $" + accountSummary.getBalance());

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");

        selectOption(accountSummary, scanner.next());
    }

    private void selectAmount(String option, Account account) {
        switch (option) {
            case "1":
                withDrawProcess("10", account);
                break;
            case "2":
                withDrawProcess("50", account);
                break;
            case "3":
                withDrawProcess("100", account);
                break;
            case "4":
                showOtherAmount(account);
                break;
            case "5":
                TransactionScreen.getInit().show(account);
                break;
            default:
                show(account);
                break;
        }
    }

    private void selectOption(Account account, String option) {
        if (option.equals("1")) {
            TransactionScreen.getInit().show(account);
        } else if(option.equals("2")) {
            WelcomeScreen.getInit().show();
        }
    }

    private void withDrawProcess(String amountStr, Account account) {
        TransactionService transactionService = TransactionService.getInit(account);

        if (WithdrawValidation.validate(amountStr, account)) {
            Integer amount = Integer.valueOf(amountStr);
            Account accountSummary = transactionService.withDraw(amount);
            showSummary(accountSummary, amount);
        }
        show(account);
    }
}
