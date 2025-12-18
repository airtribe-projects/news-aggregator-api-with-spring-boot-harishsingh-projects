package com.example.news_aggregator.news.dto;

public class NewsItemDto {

    private String title;
    private String description;
    private String url;
    private String image;
    private String publishedAt;
    private String sourceName;

    public NewsItemDto() {}

    public NewsItemDto(
            String title,
            String description,
            String url,
            String image,
            String publishedAt,
            String sourceName
    ) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
