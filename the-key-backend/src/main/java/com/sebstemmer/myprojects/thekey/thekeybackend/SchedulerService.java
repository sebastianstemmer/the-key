package com.sebstemmer.myprojects.thekey.thekeybackend;

import com.sebstemmer.myprojects.thekey.thekeybackend.data.Post;
import com.sebstemmer.myprojects.thekey.thekeybackend.data.PostComparisonResult;
import com.sebstemmer.myprojects.thekey.thekeybackend.data.PostsRepository;
import com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts.FetchPostsException;
import com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts.FetchPostsService;
import com.sebstemmer.myprojects.thekey.thekeybackend.websocket.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final static long RATE_IN_MS = 2000;

    private final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    private final FetchPostsService fetchPostsService;
    private final PostsRepository postsRepository;
    private final WebSocketService webSocketService;

    @Scheduled(fixedRate = RATE_IN_MS)
    public void run() {
        // fetch posts
        List<Post> incomingPosts;
        try {
            incomingPosts = fetchPostsService.getPosts();
        } catch(FetchPostsException e) {
            logger.error(e.getMessage(), e);
            return;
        }

        // compare existing posts with incoming posts
        List<Post> existingPosts = postsRepository.readAll();
        PostComparisonResult postComparisonResult = Post.comparePosts(incomingPosts, existingPosts);

        // update database
        if(postComparisonResult.somethingChanged()) {
            postsRepository.createAll(postComparisonResult.getNewPosts());
            postsRepository.updateAll(postComparisonResult.getChangedPosts());
            postsRepository.deleteAll(postComparisonResult.getDeletedPostsIds());
        }

        // send to frontend via websocket
        webSocketService.send(postComparisonResult);
    }
}
