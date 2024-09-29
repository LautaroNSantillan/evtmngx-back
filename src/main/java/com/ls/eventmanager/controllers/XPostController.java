package com.ls.eventmanager.controllers;

import com.ls.eventmanager.models.XEventLocation;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.repositories.XPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class XPostController {
    private final XEventRepository xEventRepository;
    private final XPostRepository xPostRepository;

}
