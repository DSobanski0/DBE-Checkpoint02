package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<User> users = userRepository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping("/new")
	public String create(User user) {
		return "user-form";
	}
	
	@PostMapping
	public ModelAndView save(@Valid User user, BindingResult result) {
		if (result.hasErrors()) return new ModelAndView("user-form");
		
		ModelAndView mv = new ModelAndView("users");
		userRepository.save(user);
		mv.addObject("users", userRepository.findAll());
		return mv;
	}
}