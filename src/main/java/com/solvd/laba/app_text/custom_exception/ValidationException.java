package com.solvd.laba.app_text.custom_exception;

public class ValidationException extends Exception {

    @Override
    public String getMessage() {
        return "Wrong data, try enter again use only letters. There must be more than two letters";
    }
}
