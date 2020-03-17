package com.mitrais.atm.repository;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.validation.AccountValidation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

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
        }

        return accountList;
    }

    public void loadDataFile(String fileName) {
        try (Stream<String> dataStream = Files.lines(Paths.get(fileName))) {
            loadDataValidation(castToAccountStream(dataStream));
        } catch (IOException ioe) {
            System.err.println("Error reading file: " + ioe);
        }
    }

    private void loadDataValidation(Stream<Account> accountStream) {
        AtomicBoolean keepAdding = new AtomicBoolean(true);
        accountStream.peek(s -> keepAdding.set(AccountValidation.validate(s)))
                .filter(s -> keepAdding.get())
                .forEach(s -> loadData().add(s));
    }

    private Stream<Account> castToAccountStream(Stream<String> dataStream) {
        return dataStream.map(row -> {
            String[] accountArr = row.split(";");

            Account account = new Account();
            account.setName(accountArr[0]);
            account.setPin(accountArr[1]);
            account.setBalance(Integer.valueOf(accountArr[2]));
            account.setAccountNumber(accountArr[3]);
            return account;
        });
    }
}
