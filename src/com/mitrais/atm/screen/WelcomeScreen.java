package com.mitrais.atm.screen;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.service.AccountService;
import com.mitrais.atm.validation.LoginValidation;
import sun.rmi.runtime.Log;

import java.util.Scanner;

public class WelcomeScreen {
    private static WelcomeScreen INIT;
    private Scanner scanner = new Scanner(System.in);

    public static WelcomeScreen getInit() {
        if (INIT == null) {
            INIT = new WelcomeScreen();
        }

        return INIT;
    }

    public void show() {
        System.out.println("----------------------------");
        String accountNumber = inputAccountNumber();
        String pin = inputPin();

        Account account = AccountService.getInit().auth(accountNumber, pin);
        if(account != null) {
            TransactionScreen.getInit().show(account);
        } else {
            System.out.println("📣 Invalid Account Number/PIN");
            show();
        }
    }

    private String inputAccountNumber() {
        String accountNumber;
        boolean validAccountNumber;

        do {
            System.out.print("Enter Account Number : ");
            accountNumber = scanner.next();
            validAccountNumber = LoginValidation.validateAccountNumber(accountNumber);
        } while (!validAccountNumber);

        return accountNumber;
    }

    private String inputPin() {
        String pin;
        boolean validatePin;

        do {
            System.out.print("Enter PIN : ");
            pin = scanner.next();
            validatePin = LoginValidation.validatePin(pin);
        } while (!validatePin);

        return pin;
    }
}
