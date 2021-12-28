package com.prajina.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prajina.academy.model.Login;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "login";
	}

	// TODO Remove hard coding
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(Model model, @ModelAttribute("login") Login login) {
		if (login != null && login.getUserName() != null & login.getPassword() != null) {
			if (login.getUserName().equals("philonoist") && login.getPassword().equals("philonoist")) {
				model.addAttribute("msg", login.getUserName());
				return "welcome";
			} else {
				model.addAttribute("error", "Invalid Details");
				return "login";
			}
		} else {
			model.addAttribute("error", "Please enter Details");
			return "login";
		}
	}
}
