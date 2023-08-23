package org.soni.service;

import lombok.AllArgsConstructor;
import org.soni.domain.Post;
import org.soni.dto.PostDto;
import org.soni.mapper.PostMapper;
import org.soni.repository.PostReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostReactiveRepository postReactiveRepository;
    private PostMapper postMapper;

    @Override
    public Mono<PostDto> save(PostDto postDto) {
        Post post = postMapper.MAPPER.mapToPost(postDto);
        post.setCreatedOn(LocalDateTime.now());
        post.setUpdatedOn(LocalDateTime.now());
        return postReactiveRepository.save(post).map(p -> {
                    postDto.setId(p.getId());
                    return postDto;
                }
        );
    }

    @Override
    public Flux<PostDto> findAllPosts() {
        return postReactiveRepository.findAll()
                .map(postMapper.MAPPER::mapToPostDto)
                .switchIfEmpty(Flux.empty());
    }

    public Boolean postExistsWithTitle(String title) {
        return postReactiveRepository.existsByTitle(title).block();
    }

    @Override
    public Mono<PostDto> update(PostDto postDto, String id) {
        return postReactiveRepository.findById(id)
                .flatMap(savedPost -> {
                    Post post = postMapper.MAPPER.mapToPost(postDto);
                    post.setId(savedPost.getId());
                    return postReactiveRepository.save(post);
                })
                .map(postMapper::mapToPostDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return postReactiveRepository.deleteById(id);
    }
}
