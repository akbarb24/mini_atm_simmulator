package com.mitrais.atm;

import com.mitrais.atm.repository.AccountRepository;
import com.mitrais.atm.screen.WelcomeScreen;

public class Main {
    public static void main(String[] args) {
        loadAccountData(args);
        WelcomeScreen.getInit().show();
    }

    private static void loadAccountData(String[] args) {
        if(args.length < 1) {
            System.out.println("📣 Please include with filePath of the CSV data source file.");
            System.out.println("   Example: /blabla/blabla/data.csv");
        } else {
            AccountRepository.getInit().loadDataFile(args[0]);
        }
    }
}
