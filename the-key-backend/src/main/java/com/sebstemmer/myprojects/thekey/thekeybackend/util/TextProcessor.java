package com.sebstemmer.myprojects.thekey.thekeybackend.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TextProcessor {
    public static String removeHtmlTags(String input) {
        return input.replaceAll("<.*?>", "");
    }

    public static String removeNewlineCharacters(String input) {
        return input.replaceAll("\\n", " ");
    }

    public static Map<String, Long> toWordCountMap(String input) {
        String[] words = input.split("\\s"); // split based on space

        Map<String, Long> wordCountMap = new HashMap<>();
        Arrays.stream(words).forEach(w -> {
                    String wordAsLowerCase = w.toLowerCase();
                    if (wordCountMap.containsKey(wordAsLowerCase)) {
                        long count = wordCountMap.get(wordAsLowerCase);
                        wordCountMap.put(wordAsLowerCase, count + 1L);
                        return;
                    }

                    wordCountMap.put(wordAsLowerCase, 1L);
                }
        );
        return wordCountMap;
    }
}
