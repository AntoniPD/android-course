package com.example.android_lesson_6.model;

public class Post {
    private Long id;
    private String title;
    private Integer photoUrl;
    private String content;

    public Post(Long id, String title, Integer photoUrl, String content) {
        this.id = id;
        this.title = title;
        this.photoUrl = photoUrl;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Integer photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
