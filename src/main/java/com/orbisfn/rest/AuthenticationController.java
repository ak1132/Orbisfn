package com.orbisfn.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orbisfn.entity.User;
import com.orbisfn.repository.UserRepository;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String email, @RequestParam String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return "User saved";
	}

	@PostMapping(path = "/login")
	public @ResponseBody String authenticate(@RequestParam String email, @RequestParam String password) {
		User user = userRepository.findUser(email, password);
		if (user == null) {
			return "User with email " + email + " not found";
		}
		return "User authenticated";
	}
}
