package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOPost;
import com.ls.eventmanager.models.XPost;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.repositories.XPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class XPostController {
    private final XEventRepository xEventRepository;
    private final XPostRepository xPostRepository;

//    @GetMapping
//    public ResponseEntity<?> getAllPosts() {
//        Set<DTOPost> organizers = xPostRepository.findAll().stream()
//                .map(DTOPost::new)
//                .collect(Collectors.toSet());
//
//        return ResponseEntity.ok(organizers);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getPost(@PathVariable UUID id){
//        Optional<XPost> post = xPostRepository.findById(id);
//        if (post.isPresent()) {
//            return ResponseEntity.ok(new DTOPost(post.get()));
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

}
