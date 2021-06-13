package com.sebstemmer.myprojects.thekey.thekeybackend.websocket;

import com.sebstemmer.myprojects.thekey.thekeybackend.data.PostComparisonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public void send(PostComparisonResult postComparisonResult) {
        PostsWebSocketDto dto = PostsWebSocketDto.createSuccessful(
                postComparisonResult.getAllIncomingPosts().stream().map(p -> new PostWebSocketDto(
                        p.getId(),
                        p.getDateTime(),
                        p.getTitle(),
                        p.getLink(),
                        p.getContentWordCountMap()
                )).collect(Collectors.toList()), postComparisonResult.somethingChanged());
        template.convertAndSend("/queue/word-count-maps", dto);
    }
}
