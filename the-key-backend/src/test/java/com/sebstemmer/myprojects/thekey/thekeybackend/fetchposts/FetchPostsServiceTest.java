package com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FetchPostsServiceTest {

    @Test
    public void responseBodyToPostHttpDtosForValidResponseBody_shouldReturnCorrectDtos() {
        String responseBody = "Ganz viel HTML <script> </script> [{\"id\": 1, \"date\": \"2021-04-23T10:47:46\", \"link\": \"https://www.example.com/\", \"title\": {\"rendered\": \"title1\"}, \"content\": {\"rendered\": \"Testcontent\", \"protected\": false}}," +
                "{\"id\": 2, \"date\": \"2021-04-21T10:21:46\", \"link\": \"https://www.google.com/\", \"title\": {\"rendered\": \"title2\"}, \"content\": {\"rendered\": \"Testcontent2\", \"protected\": false}}]";

        List<PostHttpDto> expectedOutcome = Arrays.asList(
                new PostHttpDto(1L, "2021-04-23T10:47:46", new PostHttpDto.RenderedWrapper("title1"), "https://www.example.com/", new PostHttpDto.RenderedWrapper("Testcontent")),
                new PostHttpDto(2L, "2021-04-21T10:21:46", new PostHttpDto.RenderedWrapper("title2"), "https://www.google.com/", new PostHttpDto.RenderedWrapper("Testcontent2"))
        );

        assertEquals(expectedOutcome, FetchPostsService.responseBodyToPostHttpDtos(responseBody));
    }
}
