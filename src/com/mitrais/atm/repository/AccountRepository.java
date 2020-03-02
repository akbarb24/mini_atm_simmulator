package com.mitrais.atm.repository;

import com.mitrais.atm.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static AccountRepository INIT;

    public static AccountRepository getInit(){
        if(INIT == null)
            INIT = new AccountRepository();

        return INIT;
    }

    public List<Account> loadData(){
        List<Account> accountList = new ArrayList<>();

        accountList.add(new Account("John Doe", "112233", "012108", 100));
        accountList.add(new Account("Jane Doe", "112244", "932012", 30));
        return accountList;
    }
}
