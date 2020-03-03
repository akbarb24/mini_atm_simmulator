package com.mitrais.atm.validation;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.service.AccountService;

public class TransferValidation {
    public static boolean validateAccountNumber(String accountNumber) {
        AccountService accountService = AccountService.getInit();

        if (!accountNumber.matches("[0-9]+")) {
            System.out.println("📣 Invalid account");
            return false;
        } else if (accountService.findByAccountNumber(accountNumber) == null) {
            System.out.println("📣 Invalid account");
            return false;
        }

        return true;
    }

    public static boolean validateAmount(String amountStr, Account account) {
        if (!amountStr.matches("[0-9]+")) {
            System.out.println("📣 Invalid amount");
            return false;
        }

        Integer amount = Integer.valueOf(amountStr);
        if (amount > account.getBalance()) {
            System.out.println("📣 Insufficient balance $" + amountStr);
            return false;
        } else if (amount <= 1) {
            System.out.println("📣 Minimum amount to transfer is $1");
            return false;
        } else if (amount >= 1000) {
            System.out.println("📣 Maximum to transfer is $1000");
        }

        return true;
    }
}
