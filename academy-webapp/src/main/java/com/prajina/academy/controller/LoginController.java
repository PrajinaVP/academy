package com.prajina.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prajina.academy.model.Login;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String init(Model model) {
		
		return "index";
	}
	
	@GetMapping("/academy")
	public String initAcademy(Model model) {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String l(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "login";
	}

	// TODO Remove hard coding
	@PostMapping("/login")
	public String submit(Model model, @ModelAttribute("login") Login login) {
		if (login != null && login.getUserName() != null & login.getPassword() != null) {
			if (login.getUserName().equals("philonoist") && login.getPassword().equals("philonoist")) {
				model.addAttribute("user", login.getUserName());
				return "welcome";
			} else {
				model.addAttribute("error", "Invalid Login Credentials!");
				return "login";
			}
		} else {
			model.addAttribute("error", "Please enter Details");
			return "login";
		}
	}
}
