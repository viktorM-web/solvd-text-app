package com.solvd.laba.app_text;

import com.solvd.laba.app_text.custom_exception.ExceptionHandlerUtil;
import com.solvd.laba.app_text.util.FileHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class Session implements AutoCloseable {

    private static final List<String> RESULT_SESSION = new LinkedList<>();

    Scanner scanner;

    public Session() {
        log.info("Hello");
        this.scanner = new Scanner(System.in);
    }

    public String getRequestForMenu() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    @Override
    public void close() {
        scanner.close();
        FileHelper.save(RESULT_SESSION);
    }

    public List<String> getDataFromFile() throws FileNotFoundException {
        log.info("Enter name file in resources");
        String file = scanner.nextLine();

        return FileHelper.getDataFromFile(file);
    }

    public List<String> getDataFromConsole() {
        log.info("Enter article");
        List<String> text = new ArrayList<>();
        text.add(scanner.nextLine());
        return text;
    }

    public String getRequestFroSearch() {
        log.info("Enter word which you want to find");
        return ExceptionHandlerUtil.handleValidException(scanner);
    }

    public boolean addResult(List<String> result) {
        return RESULT_SESSION.addAll(result);
    }
}
