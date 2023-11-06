package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Post;

import java.time.LocalDateTime;

public class PostResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
//        this.createdAt = post.getCreatedAt();
    }
}
