package com.sebstemmer.myprojects.thekey.thekeybackend.fetchposts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sebstemmer.myprojects.thekey.thekeybackend.data.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FetchPostsService {

    private final static String URL = "https://www.internate.org/wp-json/wp/v2/posts?_fields=id,date,title,link,content&orderby=date&order=desc&per_page=100&page=";

    private final Logger logger = LoggerFactory.getLogger(FetchPostsService.class);

    private final HttpClient httpClient;

    public FetchPostsService() {
        httpClient = HttpClient.newHttpClient();
    }

    public List<Post> getPosts() throws FetchPostsException {
        try {
            List<PostHttpDto> posts = new ArrayList<>();

            int maxPages = 100;
            // loop over all blog pages, maximum maxPages pages expected, otherwise warning
            for (int i = 1; i <= maxPages; i++) {
                if (i == 100) logger.warn("many blog pages, increase max value (currently " + maxPages + ").");

                String url = URL + i;

                HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder().GET().uri(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) break;

                posts.addAll(responseBodyToPostHttpDtos(response.body()));
            }
            return posts.stream().map(pHD -> new Post(pHD.getId(), LocalDateTime.parse(pHD.getDate()), pHD.getTitle(), pHD.getLink(), pHD.getContent())).collect(Collectors.toList());
        } catch(Exception e) {
            throw new FetchPostsException(e);
        }
    }

    static List<PostHttpDto> responseBodyToPostHttpDtos(String responseBody) {
        // remove from body to produce valid json
        int validJsonStartIndex = responseBody.indexOf("[{\"id\"");
        String validResponseBody = responseBody.substring(validJsonStartIndex);

        return new Gson().fromJson(validResponseBody, new TypeToken<ArrayList<PostHttpDto>>() {}.getType());
    }
}
