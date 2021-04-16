package com.softuni.model.service;

public class CommentServiceModel {
    private Integer score;
    private String textContent;
    private String author;

    public CommentServiceModel() {
    }

    public Integer getScore() {
        return score;
    }

    public CommentServiceModel setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
