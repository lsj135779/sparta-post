package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto create(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        PostResponseDto responseDto = new PostResponseDto(savePost);
        System.out.println(responseDto.getCreatedAt());
        return responseDto;
    }

    public PostResponseDto getPost(Long id) {
        Post post = findMemo(id);
        PostResponseDto responseDto = new PostResponseDto(post);
        return responseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findMemo(id);
        // 요청할 때 비밀번호와 DB에 저장된 비밀번호가 일치한지 확인
        if (Objects.equals(post.getPassword(), requestDto.getPassword())) {
            post.update(requestDto);
            PostResponseDto responseDto = new PostResponseDto(post);
            return responseDto;
        } else {
            PostResponseDto responseDto = new PostResponseDto("해당 게시글의 비밀번호와 일치하지 않습니다.");
            return responseDto;
        }
    }

    public PostResponseDto deletePost(Long id, PostRequestDto requestDto) {
        Post post = findMemo(id);
        if (Objects.equals(post.getPassword(), requestDto.getPassword())) {
            postRepository.delete(post);
            PostResponseDto responseDto = new PostResponseDto("해당 게시글을 삭제했습니다.");
            return responseDto;
        } else {
            PostResponseDto responseDto = new PostResponseDto("해당 게시글의 비밀번호와 일치하지 않습니다.");
            return responseDto;
        }
    }

    // id로 해당 게시글 찾기
    private Post findMemo(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
