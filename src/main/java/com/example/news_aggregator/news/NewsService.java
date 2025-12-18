/*package com.example.news_aggregator.news;

import com.example.news_aggregator.preference.Preference;
import com.example.news_aggregator.preference.PreferenceService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsService {

    private final PreferenceService preferenceService;
    private final WebClient webClient;

    public NewsService(PreferenceService preferenceService, WebClient webClient) {
        this.preferenceService = preferenceService;
        this.webClient = webClient;
    }

    public String getNewsForUser(String username) {

        // ✅ FIX: do NOT throw exception if preference missing
        Preference preference = preferenceService
                .getByUsername(username)
                .orElseGet(() -> {
                    Preference p = new Preference();
                    p.setUsername(username);
                    p.setCategory("general");
                    p.setLanguage("en");
                    p.setCountry("in");
                    return p;
                });

        // ✅ Call GNews API using EXISTING WebClient
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("category", preference.getCategory())
                        .queryParam("lang", preference.getLanguage())
                        .queryParam("country", preference.getCountry())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
*/

/*
package com.example.news_aggregator.news;

import com.example.news_aggregator.preference.Preference;
import com.example.news_aggregator.preference.PreferenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsService {

    private final WebClient webClient;
    private final PreferenceService preferenceService;

    @Value("${gnews.api.url}")
    private String gnewsUrl;

    @Value("${gnews.api.key}")
    private String apiKey;

    public NewsService(WebClient webClient, PreferenceService preferenceService) {
        this.webClient = webClient;
        this.preferenceService = preferenceService;
    }

    public String getNewsForUser(String username) {

        Preference pref = preferenceService
                .getByUsername(username)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(gnewsUrl)
                        .queryParam("category", pref.getCategory())
                        .queryParam("lang", pref.getLanguage())
                        .queryParam("country", pref.getCountry())
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
*/

/*
package com.example.news_aggregator.news;

import com.example.news_aggregator.preference.Preference;
import com.example.news_aggregator.preference.PreferenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsService {

    private final PreferenceService preferenceService;
    private final WebClient webClient;

    @Value("${gnews.api.key}")
    private String apiKey;

    @Value("${gnews.api.url}")
    private String apiUrl;

    public NewsService(PreferenceService preferenceService, WebClient webClient) {
        this.preferenceService = preferenceService;
        this.webClient = webClient;
    }

    public String getNewsForUser(String username) {

        // ✅ FIX 1: preference safely fetch
        Preference preference = preferenceService.getByUsername(username)
                .orElseThrow(() ->
                        new IllegalStateException("Please set preferences before fetching news")
                );

        // ✅ FIX 2: FULL URL build (your error was only "?category=...")
        String url = apiUrl
                + "?category=" + preference.getCategory()
                + "&lang=" + preference.getLanguage()
                + "&country=" + preference.getCountry()
                + "&apikey=" + apiKey;

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
*/
/*
package com.example.news_aggregator.news;

import com.example.news_aggregator.news.client.GNewsClient;
import com.example.news_aggregator.news.dto.GNewsResponse;
import com.example.news_aggregator.preference.Preference;
import com.example.news_aggregator.preference.PreferenceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private final GNewsClient gNewsClient;
    private final PreferenceService preferenceService;

    public NewsService(GNewsClient gNewsClient,
                       PreferenceService preferenceService) {
        this.gNewsClient = gNewsClient;
        this.preferenceService = preferenceService;
    }

    public GNewsResponse getNewsForLoggedInUser() {

        // 1️⃣ Logged-in username
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2️⃣ User preferences
        Preference preference = preferenceService
                .getByUsername(username)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));

        // 3️⃣ Call GNews API
        return gNewsClient.fetchNews(
                preference.getCategory(),
                preference.getCountry(),
                preference.getLanguage()
        );
    }
}

*/

package com.example.news_aggregator.news;

import com.example.news_aggregator.news.client.GNewsClient;
import com.example.news_aggregator.news.dto.*;
import com.example.news_aggregator.preference.Preference;
import com.example.news_aggregator.preference.PreferenceService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final GNewsClient gNewsClient;
    private final PreferenceService preferenceService;

    public NewsService(GNewsClient gNewsClient, PreferenceService preferenceService) {
        this.gNewsClient = gNewsClient;
        this.preferenceService = preferenceService;
    }

    public NewsResponseDto getNewsForLoggedInUser() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Preference preference = preferenceService
                .getByUsername(username)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));

        GNewsResponse gNewsResponse = gNewsClient.fetchNews(
                preference.getCategory(),
                preference.getCountry(),
                preference.getLanguage()
        );

        List<NewsItemDto> cleanArticles = gNewsResponse.getArticles()
                .stream()
                .map(article -> new NewsItemDto(
                        article.getTitle(),
                        article.getDescription(),
                        article.getUrl(),
                        article.getImage(),
                        article.getPublishedAt(),
                        article.getSource().getName()
                ))
                .toList();

        return new NewsResponseDto(cleanArticles);
    }
}
