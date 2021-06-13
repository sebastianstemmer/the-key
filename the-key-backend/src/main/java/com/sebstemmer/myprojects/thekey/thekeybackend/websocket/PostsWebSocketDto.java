package com.sebstemmer.myprojects.thekey.thekeybackend.websocket;

import lombok.Getter;

import java.util.List;

@Getter
public class PostsWebSocketDto {
    private final List<PostWebSocketDto> posts;
    private final Boolean somethingChanged;

    private PostsWebSocketDto(List<PostWebSocketDto> posts, boolean somethingChanged) {
       this.posts = posts;
       this.somethingChanged = somethingChanged;
    }

    public static PostsWebSocketDto createSuccessful(List<PostWebSocketDto> posts, boolean somethingChanged) {
        return new PostsWebSocketDto(posts, somethingChanged);
    }
}
