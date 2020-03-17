package com.mitrais.atm;

import com.mitrais.atm.repository.AccountRepository;
import com.mitrais.atm.screen.WelcomeScreen;

public class Main {
    public static void main(String[] args) {
        AccountRepository.getInit().loadDataFile(args[0]);

        WelcomeScreen.getInit().show();
    }
}
