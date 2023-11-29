package com.solvd.laba.app_text.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@UtilityClass
public class FileHelper {

    private static final String RESULT_SESSION_FILE = "src/main/resources/result(" + System.currentTimeMillis() + ").txt";
    private static final String MAIN_DIR = "src/main/resources/";

    public static void save(List<String> result) {
        try {
            FileUtils.writeLines(new File(RESULT_SESSION_FILE), StandardCharsets.UTF_8.name(), result, null, false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static List<String> getDataFromFile(String file) throws FileNotFoundException {
        try {
            return FileUtils.readLines(new File(MAIN_DIR + file), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new FileNotFoundException(exception.getMessage());
        }
    }
}
