package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.service.PostService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // post 메소드 ("/api/post") 게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.create(requestDto);
    }

    // get 메소드 ("/api/post/{id}") 선택한 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // get 메소드 ("/api/posts") 모든 게시글 조회
    @GetMapping("/posts")
    public PostResponseDto getPosts() {
        return postService.getPosts();
    }

    // put 메소드 ("/api/post/{id}") 게시글 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // delete 메소드 ("/api/post/{id}") 게시글 삭제
    @DeleteMapping("/post/{id}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }
}
