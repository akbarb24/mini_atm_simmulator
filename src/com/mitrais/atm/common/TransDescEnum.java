package com.mitrais.atm.common;

public enum TransDescEnum {
    WITHDRAW("Withdraw"), TRANSFER_IN("Transfer In from %s"), TRANSFER_OUT("Transfer Out to %s");

    private String value;
    TransDescEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
