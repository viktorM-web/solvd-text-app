package com.solvd.laba.app_text;

import com.solvd.laba.app_text.enam.Menu;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

@Slf4j
public class App implements AutoCloseable {

    private Session session;
    private Text text;
    boolean exitFromMainMenu;
    boolean exitFromSecondMenu;

    public App() {
        log.info("Hello");
        session = new Session();
        text = new Text();
        exitFromMainMenu = false;
        exitFromSecondMenu = false;
    }

    public void run() {
        try {
            while (!exitFromMainMenu) {
                exitFromSecondMenu = false;

                Menu.MAIN.displayOption();

                try {
                    switch (session.getRequestForMenu()) {
                        case "1" -> text.setText(session.getDataFromFile());
                        case "2" -> text.setText(session.getDataFromConsole());
                        case "0" -> exitFromMainMenu = true;
                        default -> {
                            log.error("not correct data, try again");
                            exitFromSecondMenu = true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    log.error("File not found by dir " + e.getMessage());
                    exitFromSecondMenu = true;
                }

                while (!exitFromMainMenu && !exitFromSecondMenu) {

                    Menu.SECONDARY.displayOption();

                    switch (session.getRequestForMenu()) {
                        case "1" -> session.addResult(text.displayUniqueWords());
                        case "2" -> session.addResult(text.displayListSeparateLetters());
                        case "3" -> session.addResult(text.displayCountMatchers(session.getRequestFroSearch()));
                        case "0" -> exitFromSecondMenu = true;
                        default -> log.error("not correct data, try again");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void close() {
        session.close();
    }
}
