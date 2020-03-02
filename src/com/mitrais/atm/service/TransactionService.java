package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;

public class TransactionService {
    private static TransactionService INIT;
    private Account account;

    public TransactionService(Account account) {
        this.account = account;
    }

    public static TransactionService getInit(Account account){
        if (INIT == null)
            INIT = new TransactionService(account);

        return INIT;
    }

    public Account withDraw(Integer amount) {
        Integer newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        return account;
    }
}
