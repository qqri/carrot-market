package com.qqri.postservice.web;

import com.qqri.postservice.service.PostsService;
import com.qqri.postservice.web.dto.PostsListResponseDto;
import com.qqri.postservice.web.dto.PostsResponseDto;
import com.qqri.postservice.web.dto.PostsSaveRequestDto;
import com.qqri.postservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostsListResponseDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.findAllDesc());
    }

}
