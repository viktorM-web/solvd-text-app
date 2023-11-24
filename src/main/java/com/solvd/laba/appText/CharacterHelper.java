package com.solvd.laba.appText;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

@UtilityClass
public class CharacterHelper {

    static List<String> countUniqueWords(List<String> strings) {
        List<String> result = new LinkedList<>();
        for (String str : strings) {
            if (!StringUtils.isEmpty(str)) {
                String[] s = StringUtils.split(str, " ");
                for (String check : s) {
                    String s1 = check.replaceAll("[^a-zA-Z]", "");
                    if (!StringUtils.isBlank(s1) && !result.contains(s1)) {
                        result.add(s1);
                    }
                }
            }
        }
        int numb = result.size();
        result.add(0, "UNIQUE WORDS: ");
        result.add("Original text:");
        result.addAll(strings);
        result.add("Number of unique words: " + numb);

        return result;
    }

    static List<String> splitOnLetters(List<String> strings) {
        List<String> result = new LinkedList<>();

        String chars = strings.stream()
                .map(String::chars)
                .flatMapToInt(identity())
                .mapToObj(intValue -> (char) intValue)
                .filter(Character::isLetter)
                .map(Character::toUpperCase)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        result.add("NUMBER OF LETTERS");
        result.add("Original text:");
        result.addAll(strings);
        result.add("result: " + chars);
        result.add("Number of letters: " + (chars.length() / 2 + 1));

        return result;
    }

    static List<String> findMatchers(String word, List<String> text) {

        List<String> result = new LinkedList<>();

        long count = text.stream()
                .map(it -> it.split(" "))
                .flatMap(Stream::of)
                .filter(it -> it.equalsIgnoreCase(word))
                .count();

        result.add("WORD SEARCH");
        result.add("Original text:");
        result.addAll(text);
        result.add("Search by word:" + word);
        result.add("result: found " + count + " word(s)");

        return result;
    }
}
