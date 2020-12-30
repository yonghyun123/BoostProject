package kr.or.connect.project3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.project3.service.LoginPageService;

@Controller
public class LoginPageController {
	
	@Autowired
	private LoginPageService loginPageService;
	
	@GetMapping("/loginPage")
	public String getLoginPage(){
		System.out.println("loginController=>>>");
		return "bookinglogin"; 
	}
	
	
	@GetMapping("/myReservationPage")
	public String getReservationPage(@RequestParam(name="email") String reserveEmail,
			ModelMap model){
		System.out.println("loginController=>>>"+reserveEmail);
		model.addAttribute("email", reserveEmail);
		return "myreservation"; 
	}
	
	@PostMapping("/isEmail")
	public String myPageLogin(@RequestParam(name="reserve_email") String reseveEmail,		
			HttpSession session,
			RedirectAttributes redirectAttr){
		
		System.out.println("loginController=>>>myPageLogin methods");
		String ouputEmail = loginPageService.getEmailInfo(reseveEmail);
		
		
		if(ouputEmail.equals(reseveEmail)){
			session.setAttribute("isEamil", true);
		}else{
			redirectAttr.addFlashAttribute("errorMessage","등록된 이메일이 없습니다.");
			return "redirect:/loginPage";
		}
		String returnUrl = "redirect:/myReservationPage?email="+reseveEmail;
		
		return returnUrl;
	}
}
