package com.mitrais.atm.validation;

import com.mitrais.atm.model.Account;

public class WithdrawValidation {
    public static boolean validate(String amountStr, Account account) {
        if (!amountStr.matches("[0-9]+")) {
            System.out.println("📣 Invalid amount");
            return false;
        }

        Integer amount = Integer.valueOf(amountStr);
        if (amount > 1000) {
            System.out.println("📣 Maximum amount to withdraw is $1000");
            return false;
        }
        if (amount % 10 != 0) {
            System.out.println("📣 Invalid amount");
            return false;
        }
        if (amount > account.getBalance()) {
            System.out.println(String.format("📣 Insufficient balance $%s", amountStr));
            return false;
        }

        return true;
    }
}
