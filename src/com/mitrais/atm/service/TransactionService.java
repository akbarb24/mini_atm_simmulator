package com.mitrais.atm.service;

import com.mitrais.atm.common.TransDescEnum;
import com.mitrais.atm.common.TransTypeEnum;
import com.mitrais.atm.model.Account;
import com.mitrais.atm.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {
    private static TransactionService INIT;
    private Account account;

    public TransactionService(Account account) {
        this.account = account;
    }

    public List<Transaction> loadData(Account account) {
        List<Transaction> transactionList = account.getTransactionList();
        if(transactionList == null) {
            transactionList = new ArrayList<>();
            account.setTransactionList(transactionList);
        }

        return transactionList;
    }

    public Account withDraw(Integer amount) {
        Integer newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        record(account, TransDescEnum.WITHDRAW, amount);

        return account;
    }

    public Account transfer(Account destAccount, Integer amount) {
        Integer newBalance;

        //deduct the user balance
        newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        record(account, TransDescEnum.TRANSFER_OUT, amount);

        //add balance to dest account
        newBalance = destAccount.getBalance() + amount;
        destAccount.setBalance(newBalance);
        record(destAccount, TransDescEnum.TRANSFER_IN, amount);

        return account;
    }

    private void record(Account account, TransDescEnum transDesc, Integer amount) {
        String description = getTransactionDesc(transDesc, account);
        String transType = getTransactionType(transDesc);
        LocalDateTime transactionDate = LocalDateTime.now();

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setDescription(description);
        transaction.setTransactionType(transType);
        transaction.setAmount(amount);
        loadData(account).add(transaction);
    }

    private String getTransactionDesc(TransDescEnum transDescEnum, Account accountState) {
        String transDesc = "";
        switch (transDescEnum) {
            case TRANSFER_IN:
                transDesc = String.format(TransDescEnum.TRANSFER_IN.getValue(), account.getAccountNumber());
                break;
            case TRANSFER_OUT:
                // Bug: the desc should displays "Transfer Out to #{dest_account_number}" not "Transfer Out to #{account_number}"
                // Still dunno the solution.
                // Indeed, I can only display the desc by simple words: "Transfer out."
                // But It's not cool, right?
                transDesc = String.format(TransDescEnum.TRANSFER_OUT.getValue(), accountState.getAccountNumber());
                break;
            case WITHDRAW:
                transDesc = TransDescEnum.WITHDRAW.getValue();
                break;
        }

        return transDesc;
    }

    public String getTransactionType(TransDescEnum transDescEnum) {
        String transactionType = "";
        switch (transDescEnum){
            case TRANSFER_IN:
                transactionType = TransTypeEnum.TRANSFER_IN.getValue();
                break;
            case TRANSFER_OUT:
                transactionType =  TransTypeEnum.TRANSFER_OUT.getValue();
                break;
            case WITHDRAW:
                transactionType = TransTypeEnum.WITHDRAW.getValue();
        }

        return transactionType;
    }
}
