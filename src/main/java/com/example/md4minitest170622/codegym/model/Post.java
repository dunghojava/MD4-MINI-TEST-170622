package com.example.md4minitest170622.codegym.model;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @CreatedDate
    private Date createAt;

    private int likes;

    private String image;

    public Post(String title, String content, Date createAt, int likes, String image) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.likes = likes;
        this.image = image;
    }

    public Post() {
    }

    public Post(String title, String content, Date createAt, int likes) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
