package com.prajina.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ModuleController {

	@GetMapping("/academy/module")
	public String getModule() {
		return "module";
	}
}
