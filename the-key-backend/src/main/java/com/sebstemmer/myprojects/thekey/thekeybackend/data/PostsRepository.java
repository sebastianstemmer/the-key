package com.sebstemmer.myprojects.thekey.thekeybackend.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class PostsRepository {
    private final Map<Long, Post> posts;

    public PostsRepository() {
        posts = new HashMap<>();
    }

    public void createAll(List<Post> posts) {
        Map<Long, Post> postCopies = posts.stream().map(Post::new).collect(Collectors.toMap(Post::getId, Function.identity()));
        this.posts.putAll(postCopies);
    }

    public List<Post> readAll() {
        return posts.values().stream().map(Post::new).collect(Collectors.toList());
    }

    public void updateAll(List<Post> posts) {
        deleteAll(posts.stream().map(Post::getId).collect(Collectors.toList()));
        createAll(posts);
    }

    public void deleteAll(List<Long> postsIds) {
        posts.keySet().removeAll(postsIds);
    }
}
