package com.solvd.laba.appText.customException;

public class BackToMainMenuException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Back to main menu";
    }
}
