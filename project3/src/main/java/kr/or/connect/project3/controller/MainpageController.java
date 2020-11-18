package kr.or.connect.project3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainpageController {
	@GetMapping(path="/mainpage")
	public String showMainpage(){
		return "index";
	}
}
