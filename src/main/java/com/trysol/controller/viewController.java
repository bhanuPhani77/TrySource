package com.trysol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trysol.entity.EmployeeDetails;
import com.trysol.securityconfig.PasswordEncoderConfigure;
import com.trysol.service.EmployeeDetailsService;

@Controller
public class viewController {
	@Autowired
	private EmployeeDetailsService detailsService;
	@Autowired
	PasswordEncoderConfigure encoder;
	@GetMapping("/login")
    public String login(Model m) {
        return "login";
    }

    @GetMapping({ "/index", "/" })
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "userdetails";
    }
    @PostMapping("/authenticateUser")
    public String logProcess(@ModelAttribute UserDetails details) {
    	String username = details.getUsername();
    	String password = encoder.passwordEncoder().encode(details.getPassword());
    	UserDetails loadUserByUsername = detailsService.loadUserByUsername(username);
    	if(loadUserByUsername.getUsername().equals(username) && loadUserByUsername.getPassword().equals(password)) {
    		return "index";
    	}else {
    		return "login";
    	}
    }
}
