package com.solvd.laba.app_text.custom_exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@UtilityClass
public class ExceptionHandlerUtil {

    public static final String PATTERN_WORLD_FOR_SEARCHING = "^[a-zA-Z]{3,}";

    public static String handleValidException(Scanner scanner) {

        boolean wasException = true;
        String str = null;

        while (wasException) {
            try {
                str = scanner.nextLine();
                checkWordForSearchingPattern(str);
                wasException = false;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return str;
    }

    private static void checkWordForSearchingPattern(String str) throws ValidationException {
        if (!str.matches(PATTERN_WORLD_FOR_SEARCHING)) {
            throw new ValidationException();
        }
    }
}
