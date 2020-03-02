package com.mitrais.atm.validation;

public class LoginValidation {
    public static boolean validateAccountNumber(String accountNumber) {
        if (!accountNumber.matches("[0-9]+")) {
            System.out.println("📣 Account Number should only contains numbers");
            return false;
        }

        if (accountNumber.length() < 6) {
            System.out.println("📣 Account Number should have 6 digits length");
            return false;
        }

        return true;
    }

    public static boolean validatePin(String pin) {
        if (!pin.matches("[0-9]+")) {
            System.out.println("📣 PIN should only contains numbers");
            return false;
        }

        if (pin.length() < 6) {
            System.out.println("📣 PIN should have 6 digits length");
            return false;
        }

        return true;
    }
}
