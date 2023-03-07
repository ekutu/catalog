package com.nexus.catalog.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.nexus.catalog.config.NexusProperties;

@RestController
public class HomeController {

    private final NexusProperties nexusProperties;

    public HomeController(NexusProperties nexusProperties) {
        this.nexusProperties = nexusProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return this.nexusProperties.getGreeting();
    }
}
