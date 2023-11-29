package com.solvd.laba.app_text.enam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Menu {

    MAIN("\nif you want to work with with file enter 1 " +
            "\nif you want work with console enter 2 " +
            "\nif you want to EXIT enter 0"),
    SECONDARY("What do you want to do? " +
            "\n Count the number of unique words in the text (case insensitive) enter 1 " +
            "\n Divide the text into letters and count the number of letters in the text enter 2 " +
            "\n Check if there is a word (more than 2 letters) in the text, not case sensitive enter 3" +
            "\n if you want to return to main menu enter 0");

    private String option;

    Menu(String option) {
        this.option = option;
    }

    public String displayOption() {
        log.info(option);
        return option;
    }
}
