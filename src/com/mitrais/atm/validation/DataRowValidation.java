package com.mitrais.atm.validation;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class DataRowValidation {
    public final static int LENGTH = 4;

    public static boolean isValid(int rowIndex, String row, String delimiter) {
        String[] array = row.split(delimiter);
        if (checkSize(rowIndex, array))
            return !isEmptyString(rowIndex, array) && isNumeric(rowIndex, array[2]);
        return false;
    }

    public static boolean checkSize(int rowIndex, String[] array) {
        boolean valid = (array.length == LENGTH);
        if (!valid)
            System.out.println(String.format("📣 Skip row %d. The column size of row data isn't 4.", rowIndex));
        return valid;
    }

    private static boolean isEmptyString(int rowIndex, String[] rowArr) {
        boolean isEmpty = Arrays.stream(rowArr).anyMatch(String::isEmpty);
        if (isEmpty)
            System.out.println(String.format("📣 Skip row %d. The Data contains empty value(s).", rowIndex));
        return isEmpty;
    }

    private static boolean isNumeric(int rowIndex, String element) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        boolean isNum = pattern.matcher(element).matches();
        if (!isNum)
            System.out.println(String.format("📣 Skip row %d. The balance column isn't numeric.", rowIndex));
        return isNum;
    }
}
