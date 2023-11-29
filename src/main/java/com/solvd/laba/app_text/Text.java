package com.solvd.laba.app_text;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

@Slf4j
@AllArgsConstructor
public class Text {

    private final List<String> currentText;

    public Text() {
        this.currentText = new ArrayList<>();
    }

    public List<String> displayUniqueWords() {
        List<String> result = listUniqueWords();

        int numb = result.size();
        result.add(0, "UNIQUE WORDS: ");
        result.add("Original text:");
        result.addAll(currentText);
        result.add("Number of unique words: " + numb);
        result.add("");

        result.forEach(log::info);

        return result;
    }

    public List<String> listUniqueWords() {
        List<String> result = new LinkedList<>();
        for (String str : currentText) {
            if (!StringUtils.isEmpty(str)) {
                String[] s = StringUtils.split(str, " ");
                for (String check : s) {
                    String s1 = check.replaceAll("[^a-zA-Z]", "");
                    s1 = s1.toUpperCase();
                    if (!StringUtils.isBlank(s1) && !result.contains(s1)) {
                        result.add(s1);
                    }
                }
            }
        }
        return result;
    }

    public List<String> displayListSeparateLetters() {
        String chars = listSeparateLetters();
        List<String> result = new LinkedList<>();

        result.add("NUMBER OF LETTERS");
        result.add("Original text:");
        result.addAll(currentText);
        result.add("result: " + chars);
        result.add("Number of letters: " + (chars.length() / 2 + 1));
        result.add("");

        result.forEach(log::info);

        return result;
    }

    public String listSeparateLetters() {
        return currentText.stream()
                .map(String::chars)
                .flatMapToInt(identity())
                .mapToObj(intValue -> (char) intValue)
                .filter(Character::isLetter)
                .map(Character::toUpperCase)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public List<String> displayCountMatchers(String word) {
        List<String> result = new LinkedList<>();
        Long count = countMatchers(word);

        result.add("WORD SEARCH");
        result.add("Original text:");
        result.addAll(currentText);
        result.add("Search by word:" + word);
        result.add("result: found " + count + " word(s)");
        result.add("");

        result.forEach(log::info);

        return result;
    }

    public Long countMatchers(String word) {

        return currentText.stream()
                .map(it -> it.split(" "))
                .flatMap(Stream::of)
                .map(v -> v.replaceAll("[^a-zA-Z]", ""))
                .filter(it -> it.equalsIgnoreCase(word))
                .count();
    }

    public boolean setText(List<String> text) {
        currentText.clear();
        return currentText.addAll(text);
    }
}
