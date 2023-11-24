package com.solvd.laba.appText;

import com.solvd.laba.appText.customException.BackToMainMenuException;
import com.solvd.laba.appText.customException.ExceptionHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

@Slf4j
public class App {

    private static final String RESULT_SESSION_FILE = "src/main/resources/result(" + System.currentTimeMillis() + ").txt";
    private static final String MAIN_DIR = "src/main/resources/";

    public static void main(String[] args) {

        log.info("Hello!!!");
        List<String> resultSession = new LinkedList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                log.info("\nif you want to work with with file enter 1 " +
                        "\nif you want work with console enter 2 " +
                        "\nif you want to exit enter EXIT");
                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    try {
                        if (command.equals("1")) {
                            resultSession.addAll(workWithText(scanner, App::getTextFromFile));
                            resultSession.add("");
                        } else if (command.equals("2")) {
                            resultSession.addAll(workWithText(scanner, App::getTextFromConsole));
                            resultSession.add("");
                        } else if (command.equalsIgnoreCase("EXIT")) {
                            break;
                        } else {
                            log.info("not correct data, try again");
                        }
                    } catch (BackToMainMenuException e) {
                        log.info(e.getMessage());
                    }
                }
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        } finally {
            saveResult(resultSession);
            log.info("finish work");
        }
    }

    private static List<String> workWithText(Scanner scanner, Function<Scanner, List<String>> getText) {

        List<String> result = List.of();
        List<String> text = getText.apply(scanner);
        boolean isValidCommand = true;

        do {
            isValidCommand = true;
            displayAvailableCommands();
            String command = scanner.nextLine();
            if (command.equals("1")) {
                result = CharacterHelper.countUniqueWords(text);
            } else if (command.equals("2")) {
                result = CharacterHelper.splitOnLetters(text);
            } else if (command.equals("3")) {
                String word = getWordForSearch(scanner);
                result = CharacterHelper.findMatchers(word, text);
            } else if (command.equalsIgnoreCase("back")) {
                throw new BackToMainMenuException();
            } else {
                isValidCommand = false;
                log.info("wrong data, try again");
            }
        } while (!isValidCommand);
        result.forEach(log::info);
        return result;
    }

    private static List<String> getTextFromConsole(Scanner scanner) {
        log.info("Enter article");
        List<String> text = new ArrayList<>();
        text.add(scanner.nextLine());
        return text;
    }

    private static List<String> getTextFromFile(Scanner scanner) {
        log.info("Enter name file in resources");
        String file = scanner.nextLine();

        try {
            return FileUtils.readLines(new File(MAIN_DIR + file), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
            log.info("File not found");
            throw new BackToMainMenuException();
        }
    }

    private static String getWordForSearch(Scanner scanner) {
        log.info("Enter word which you want to find");
        return ExceptionHandlerUtil.handleValidException(scanner);
    }

    private static void displayAvailableCommands() {
        log.info("What do you want to do? " +
                "\n Count the number of unique words in the text (case insensitive) enter 1 " +
                "\n Divide the text into letters and count the number of letters in the text enter 2 " +
                "\n Check if there is a word (more than 2 letters) in the text, not case sensitive enter 3" +
                "\n if you want to return to main menu enter BACK");
    }

    private static void saveResult(List<String> strings) {
        try {
            FileUtils.writeLines(new File(RESULT_SESSION_FILE), StandardCharsets.UTF_8.name(), strings, null, false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
