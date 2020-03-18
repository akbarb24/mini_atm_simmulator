package com.mitrais.atm.common;

public enum TransTypeEnum {
    WITHDRAW("CR"), TRANSFER_IN("DB"), TRANSFER_OUT("CR");

    private String value;
    TransTypeEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
