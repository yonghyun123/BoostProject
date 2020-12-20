package kr.or.connect.project3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {
	
	@GetMapping("/loginPage")
	public String getLoginPage(){
		System.out.println("loginController=>>>");
		return "bookinglogin"; 
	}
}
