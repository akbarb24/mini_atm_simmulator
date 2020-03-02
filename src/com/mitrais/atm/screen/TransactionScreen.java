package com.mitrais.atm.screen;

import com.mitrais.atm.model.Account;

import java.util.Scanner;

public class TransactionScreen {
    private static TransactionScreen INIT;
    private Scanner scanner = new Scanner(System.in);

    public static TransactionScreen getInit(){
        if (INIT == null)
            INIT = new TransactionScreen();

        return INIT;
    }

    public void show(Account account) {
        System.out.println("----------------------------");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");

        selectTransaction(scanner.nextLine(), account);
    }

    private void selectTransaction(String option, Account account) {
        WelcomeScreen welcomeScreen = WelcomeScreen.getInit();
        WithdrawScreen withdrawScreen = WithdrawScreen.getInit();

        switch (option) {
            case "1":
                withdrawScreen.show(account);
                break;
            case "2":
//                TransferScreen.show(account);
                break;
            case "3":
                welcomeScreen.show();
                break;
            default:
                show(account);
                break;
        }
    }
}
