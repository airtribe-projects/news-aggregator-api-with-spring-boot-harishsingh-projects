package com.example.news_aggregator.news.dto;

import java.util.List;

public class NewsResponseDto {

    private List<NewsItemDto> articles;

    public NewsResponseDto(List<NewsItemDto> articles) {
        this.articles = articles;
    }

    public List<NewsItemDto> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsItemDto> articles) {
        this.articles = articles;
    }
}
