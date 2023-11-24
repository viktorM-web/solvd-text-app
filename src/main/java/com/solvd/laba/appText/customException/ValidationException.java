package com.solvd.laba.appText.customException;

public class ValidationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Wrong data, try enter again use only letters. There must be more than two letters";
    }
}
