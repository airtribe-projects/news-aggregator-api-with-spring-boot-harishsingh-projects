package com.example.news_aggregator.news.dto;

import java.util.List;

public class GNewsResponse {

    private int totalArticles;
    private List<ArticleDto> articles;

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDto> articles) {
        this.articles = articles;
    }
}
