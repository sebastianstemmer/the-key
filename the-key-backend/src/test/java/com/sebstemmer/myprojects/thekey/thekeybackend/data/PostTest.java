package com.sebstemmer.myprojects.thekey.thekeybackend.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    public void comparePostsWithDifferences_shouldGiveCorrectPostComparisonResultWithSomethingChangedTrue() {

        List<Post> newPosts = Arrays.asList(
                new Post(5L, LocalDateTime.now().minusDays(5L), "title5", "link5", "content5"),
                new Post(6L, LocalDateTime.now().minusDays(6L), "title6", "link6", "content6")
        );
        List<Post> unchangedPosts = Collections.singletonList(
                new Post(0L, LocalDateTime.now(), "title0", "link0", "content0")
        );
        List<Post> changedPosts = Arrays.asList(
                new Post(1L, LocalDateTime.now().minusDays(1L), "title1c", "link1", "content1"),
                new Post(2L, LocalDateTime.now().minusDays(2L), "title2", "link2", "content2c")
        );


        List<Post> incomingPosts = Arrays.asList(
                unchangedPosts.get(0),
                changedPosts.get(0),
                changedPosts.get(1),
                newPosts.get(0),
                newPosts.get(1)
        );

        List<Post> existingPosts = Arrays.asList(
                unchangedPosts.get(0),
                new Post(1L, LocalDateTime.now().minusDays(1L), "title1", "link1", "content1"),
                new Post(2L, LocalDateTime.now().minusDays(2L), "title2", "link2", "content2"),
                new Post(3L, LocalDateTime.now().minusDays(3L), "title3", "link3", "content3"),
                new Post(4L, LocalDateTime.now().minusDays(4L), "title4", "link4", "content4")
        );

        PostComparisonResult result = Post.comparePosts(incomingPosts, existingPosts);

        assertEquals(incomingPosts, result.getAllIncomingPosts());
        assertEquals(newPosts, result.getNewPosts());
        assertEquals(unchangedPosts, result.getUnchangedPosts());
        assertEquals(changedPosts, result.getChangedPosts());
        assertEquals(Arrays.asList(3L, 4L), result.getDeletedPostsIds());
        assertTrue(result.somethingChanged());
    }

    @Test
    public void comparePostsWithoutDifferences_shouldGiveCorrectPostComparisonResultWithSomethingChangedFalse() {
        List<Post> incomingPosts = Arrays.asList(
                new Post(0L, LocalDateTime.now(), "title0", "link0", "content0"),
                new Post(1L, LocalDateTime.now().minusDays(1L), "title1", "link1", "content1"),
                new Post(2L, LocalDateTime.now().minusDays(2L), "title2", "link2", "content2"),
                new Post(3L, LocalDateTime.now().minusDays(3L), "title3", "link3", "content3"),
                new Post(4L, LocalDateTime.now().minusDays(4L), "title4", "link4", "content4")
        );

        PostComparisonResult result = Post.comparePosts(incomingPosts, incomingPosts);

        assertEquals(incomingPosts, result.getAllIncomingPosts());
        assertEquals(new ArrayList<>(), result.getNewPosts());
        assertEquals(incomingPosts, result.getUnchangedPosts());
        assertEquals(new ArrayList<>(), result.getChangedPosts());
        assertEquals(new ArrayList<>(), result.getDeletedPostsIds());
        assertFalse(result.somethingChanged());
    }
}
