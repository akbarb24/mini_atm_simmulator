package com.mitrais.atm;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.repository.AccountRepository;
import com.mitrais.atm.screen.WelcomeScreen;

public class Main {
    public static void main(String[] args) {
//        Integer.valueOf("ah");
        loadAccountData(args);
        WelcomeScreen.getInit().show();
    }

    private static void loadAccountData(String[] args) {
        AccountRepository account = AccountRepository.getInit();

        if(args.length < 1) {
            account.loadData().add(new Account("John Doe", "112233", "012108", 100));
            account.loadData().add(new Account("Jane Doe", "112244", "932012", 30));
        } else {
            account.loadDataFile(args[0]);
        }
    }
}
