package br.com.fiap.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/task/login")
	public String login() {
		return "login";
	}
}