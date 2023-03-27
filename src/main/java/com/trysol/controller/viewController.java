package com.trysol.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {
	@GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({ "/index", "/" })
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "userdetails";
    }
}
