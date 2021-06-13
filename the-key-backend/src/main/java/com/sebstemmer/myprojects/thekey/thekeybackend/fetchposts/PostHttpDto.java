package com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts;

import com.sebstemmer.myprojects.thekey.thekeybackend.util.TextProcessor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class PostHttpDto {

    @AllArgsConstructor
    static class RenderedWrapper {
        private String rendered;
    }

    @Getter
    private Long id;

    @Getter
    private String date;

    private RenderedWrapper title;

    @Getter
    private String link;

    private RenderedWrapper content;

    public String getTitle() {
        return title.rendered;
    }

    public String getContent() {
        return TextProcessor.removeNewlineCharacters(TextProcessor.removeHtmlTags(content.rendered));
    }
}
