package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.repository.AccountRepository;

public class AccountService {
    private static AccountService INIT;
    private AccountRepository accountRepository = AccountRepository.getInit();

    public static AccountService getInit() {
        if (INIT == null) {
            INIT = new AccountService();
        }

        return INIT;
    }

    public Account auth(String accountNumber, String pin) {
        return accountRepository.loadData().stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber())
                        && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.loadData().stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findAny()
                .orElse(null);
    }
}
