package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;

public class TransactionService {
    private static TransactionService INIT;
    private Account account;

    public TransactionService(Account account) {
        this.account = account;
    }

    public Account withDraw(Integer amount) {
        Integer newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        return account;
    }

    public Account transfer(Account destAccount, Integer amount) {
        Integer newBalance;

        //deduct the user balance
        newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

        //add balance to dest account
        newBalance = destAccount.getBalance() + amount;
        destAccount.setBalance(newBalance);

        return account;
    }
}
