package com.sebstemmer.myprojects.thekey.thekeybackend.data;

import com.sebstemmer.myprojects.thekey.thekeybackend.util.TextProcessor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Post {
    private final Long id;
    private final LocalDateTime dateTime;
    private final String title;
    private final String link;
    private final String content;

    public Post(Post post) {
        this.id = post.getId();
        this.dateTime = post.getDateTime();
        this.title = post.getTitle();
        this.link = post.getLink();
        this.content = post.getContent();
    }

    public Map<String, Long> getContentWordCountMap() {
        return TextProcessor.toWordCountMap(content);
    }

    public static PostComparisonResult comparePosts(List<Post> incomingPosts, List<Post> existingPosts) {
        Map<Long, Post> existingPostsMap = existingPosts.stream().collect(Collectors.toMap(Post::getId, Function.identity()));

        PostComparisonResult result = new PostComparisonResult(incomingPosts);

        incomingPosts.forEach(post -> {
            Post existingPost = existingPostsMap.get(post.getId());

            if(existingPost == null) {
                result.addNewPost(post);
                return;
            }

            existingPostsMap.remove(post.getId());

            if(existingPost.equals(post)) {
                result.addUnchangedPost(post);
                return;
            }

            result.addChangedPost(post);
        });
        result.addDeletedPostsIds(existingPostsMap.keySet().stream().toList());
        return result;
    }
}
