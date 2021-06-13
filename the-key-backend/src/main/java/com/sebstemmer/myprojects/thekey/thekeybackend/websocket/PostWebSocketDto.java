package com.sebstemmer.myprojects.thekey.thekeybackend.websocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class PostWebSocketDto {
    private final Long id;
    private final LocalDateTime dateTime;
    private final String title;
    private final String link;
    private final Map<String, Long> wordCountMap;
}
