package com.example.news_aggregator.news.client;

import com.example.news_aggregator.news.dto.GNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GNewsClient {

    private final RestTemplate restTemplate;

    @Value("${gnews.api.key}")
    private String apiKey;

    private static final String BASE_URL =
            "https://gnews.io/api/v4/top-headlines";

    public GNewsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GNewsResponse fetchNews(String category, String country, String language) {

        String url = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .queryParam("category", category)
                .queryParam("country", country)
                .queryParam("lang", language)
                .queryParam("max", 10)
                .queryParam("apikey", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, GNewsResponse.class);
    }
}
