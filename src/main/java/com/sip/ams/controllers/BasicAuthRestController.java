package com.sip.ams.controllers;

import com.sip.ams.entities.User;
import com.sip.ams.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sip.ams.entities.AuthenticationBean;


@CrossOrigin(origins = "*")
@RestController
public class BasicAuthRestController {

	/*@GetMapping(path = "/basicauth")
public AuthenticationBean basicauth() {
return new AuthenticationBean("You are authenticated");
}*/
	 @Autowired
	    private UserService userService;
	    @GetMapping(path = "/basicauth")
	    public User basicauth() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        System.out.println(user);
	        return user;
	    }
}