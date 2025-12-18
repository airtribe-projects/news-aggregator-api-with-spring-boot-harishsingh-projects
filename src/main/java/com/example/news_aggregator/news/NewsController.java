/*package com.example.news_aggregator.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/news")
    public String getNews(
            @RequestParam(defaultValue = "general") String category
    ) {
        return newsService.getNewsForUser(category);
    }

}*/
/*
package com.example.news_aggregator.news;

import com.example.news_aggregator.news.dto.GNewsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<GNewsResponse> getNews() {

        GNewsResponse response = newsService.getNewsForLoggedInUser();
        return ResponseEntity.ok(response);
    }
}
*/

package com.example.news_aggregator.news;

import com.example.news_aggregator.news.dto.NewsResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public NewsResponseDto getNews() {
        return newsService.getNewsForLoggedInUser();
    }
}
