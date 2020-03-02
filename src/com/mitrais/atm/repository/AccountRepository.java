package com.mitrais.atm.repository;

import com.mitrais.atm.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static AccountRepository INIT;
    private List<Account> accountList;

    public static AccountRepository getInit() {
        if (INIT == null)
            INIT = new AccountRepository();

        return INIT;
    }

    public List<Account> loadData() {
        if (accountList == null) {
            accountList = new ArrayList<>();
            accountList.add(new Account("John Doe", "112233", "012108", 100));
            accountList.add(new Account("Jane Doe", "112244", "932012", 30));
        }

        return accountList;
    }
}
