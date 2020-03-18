package com.mitrais.atm.validation;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.repository.AccountRepository;

import java.util.List;

public class AccountValidation {
    public static boolean validate(Account account) {
        List<Account> existDataList = AccountRepository.getInit().loadData();
        return checkAccountDuplicate(existDataList, account) && checkAccNumberDuplicate(existDataList, account);
    }

    private static boolean checkAccNumberDuplicate(List<Account> accountList, Account newAccount) {
        boolean valid = accountList.stream().noneMatch(account ->
                account.getAccountNumber().equals(newAccount.getAccountNumber()));
        if (!valid)
            System.out.println("📣 There can't be 2 different accounts with the same Account Number");

        return valid;
    }

    private static boolean checkAccountDuplicate(List<Account> accountList, Account newAccount) {
        boolean valid = accountList.stream().noneMatch(account ->
                account.getName().equalsIgnoreCase(newAccount.getName())
                        && account.getAccountNumber().equals(newAccount.getAccountNumber())
                        && account.getPin().equals(newAccount.getPin()));

        if (!valid)
            System.out.println("📣 There can't be duplicated records: " + newAccount);

        return valid;
    }
}
