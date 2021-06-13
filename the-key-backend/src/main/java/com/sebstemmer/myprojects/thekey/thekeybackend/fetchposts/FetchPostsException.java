package com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts;

public class FetchPostsException extends RuntimeException {
    public FetchPostsException(Exception e) {
        super("error fetching posts via http.", e);
    }
}
