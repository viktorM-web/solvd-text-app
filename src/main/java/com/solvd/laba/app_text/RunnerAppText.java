package com.solvd.laba.app_text;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnerAppText {

    public static void main(String[] args) {
        try (App app = new App()) {
            app.run();
        }
    }
}
