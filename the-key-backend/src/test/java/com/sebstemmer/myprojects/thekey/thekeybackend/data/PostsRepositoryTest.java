package com.sebstemmer.myprojects.thekey.thekeybackend.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostsRepositoryTest {

    private final List<Post> posts = Arrays.asList(
            new Post(0L, LocalDateTime.now(), "title0", "link0", "content0"),
            new Post(1L, LocalDateTime.now().minusDays(1L), "title1", "link1", "content1"),
            new Post(2L, LocalDateTime.now().minusDays(2L), "title2", "link2", "content2"),
            new Post(3L, LocalDateTime.now().minusDays(3L), "title3", "link3", "content3"),
            new Post(4L, LocalDateTime.now().minusDays(4L), "title4", "link4", "content4")
    );

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void createPosts_readAllShouldReturnAllCreatedPosts() {
        postsRepository.createAll(posts);
        assertEquals(posts, postsRepository.readAll());
    }

    @Test
    public void updatePosts_readAllShouldReturnUnchangedAndUpdatedPosts() {
        postsRepository.createAll(posts);

        List<Post> postsForUpdate = Arrays.asList(
                new Post(3L, LocalDateTime.now().minusDays(3L), "title3u", "link3u", "content3u"),
                new Post(4L, LocalDateTime.now().minusDays(4L), "title4u", "link4u", "content4u")
        );

        postsRepository.updateAll(postsForUpdate);

        assertEquals(Arrays.asList(posts.get(0), posts.get(1), posts.get(2), postsForUpdate.get(0), postsForUpdate.get(1)), postsRepository.readAll());
    }

    @Test
    public void deletePosts_readAllShouldReturnLeftOverPosts() {
        postsRepository.createAll(posts);

        List<Long> postsIdsForDeletion = Arrays.asList(3L, 4L);

        postsRepository.deleteAll(postsIdsForDeletion);

        assertEquals(Arrays.asList(posts.get(0), posts.get(1), posts.get(2)), postsRepository.readAll());
    }
}
