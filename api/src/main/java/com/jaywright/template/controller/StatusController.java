package com.jaywright.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/check")
    public String getStatus() {
        return "OK";
    }

    @GetMapping("/authenticate")
    public String getStatus(Principal principal) {
        return "Hello, " + principal.getName();
    }

}
