package com.sebstemmer.myprojects.thekey.thekeybackend.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostComparisonResult {
    private final List<Post> allIncomingPosts;

    private final List<Post> newPosts = new ArrayList<>();
    private final List<Post> unchangedPosts = new ArrayList<>();
    private final List<Post> changedPosts = new ArrayList<>();
    private final List<Long> deletedPostsIds = new ArrayList<>();

    public PostComparisonResult(List<Post> allIncomingPosts) {
        this.allIncomingPosts = allIncomingPosts;
    }

    public void addNewPost(Post post) {
        newPosts.add(post);
    }

    public void addUnchangedPost(Post post) {
        unchangedPosts.add(post);
    }

    public void addChangedPost(Post post) {
        changedPosts.add(post);
    }

    public void addDeletedPostsIds(List<Long> postsIds) {
        deletedPostsIds.addAll(postsIds);
    }

    public boolean somethingChanged() {
        return newPosts.size() != 0 || changedPosts.size() != 0 || deletedPostsIds.size() != 0;
    }
}
