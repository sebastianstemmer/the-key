package com.sebstemmer.myprojects.thekey.thekeybackend.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextProcessorTest {

    private final static String TEXT_UNDER_TEST =  "<p>Wir sind in stetem Kontakt mit den Internaten und\nwissen sehr genau, wo z. B. in <a href=\"https://www.internate.org/england/\">England </a>auch\nkurzfristig noch Plätze frei sind.</p>";
    private final static String TEXT_UNDER_TEST_WITHOUT_HTML =  "Wir sind in stetem Kontakt mit den Internaten und\nwissen sehr genau, wo z. B. in England auch\nkurzfristig noch Plätze frei sind.";
    private final static String TEXT_UNDER_TEST_WITHOUT_HTML_AND_NEWLINE_CHARS =  "Wir sind in stetem Kontakt mit den Internaten und wissen sehr genau, wo z. B. in England auch kurzfristig noch Plätze frei sind.";


    @Test
    public void removeHtmlTags_shouldRemoveAllHtmlTags() {
       assertEquals(TEXT_UNDER_TEST_WITHOUT_HTML, TextProcessor.removeHtmlTags(TEXT_UNDER_TEST));
    }

    @Test
    public void removeNewlineCharacters_shouldRemoveNewlineCharacters() {
        assertEquals(TEXT_UNDER_TEST_WITHOUT_HTML_AND_NEWLINE_CHARS, TextProcessor.removeNewlineCharacters(TEXT_UNDER_TEST_WITHOUT_HTML));
    }

    @Test
    public void toWordCountMap_shouldCreateWordCountMap() {
        Map<String, Long> wordCountMap = new HashMap<>();
        wordCountMap.put("wir", 1L);
        wordCountMap.put("sind", 1L);
        wordCountMap.put("in", 2L);
        wordCountMap.put("stetem", 1L);
        wordCountMap.put("kontakt", 1L);
        wordCountMap.put("mit", 1L);
        wordCountMap.put("den", 1L);
        wordCountMap.put("internaten", 1L);
        wordCountMap.put("und", 1L);
        wordCountMap.put("wissen", 1L);
        wordCountMap.put("sehr", 1L);
        wordCountMap.put("genau,", 1L);
        wordCountMap.put("wo", 1L);
        wordCountMap.put("z.", 1L);
        wordCountMap.put("b.", 1L);
        wordCountMap.put("england", 1L);
        wordCountMap.put("auch", 1L);
        wordCountMap.put("kurzfristig", 1L);
        wordCountMap.put("noch", 1L);
        wordCountMap.put("plätze", 1L);
        wordCountMap.put("frei", 1L);
        wordCountMap.put("sind.", 1L);

        assertEquals(wordCountMap, TextProcessor.toWordCountMap(TEXT_UNDER_TEST_WITHOUT_HTML_AND_NEWLINE_CHARS));
    }
}
