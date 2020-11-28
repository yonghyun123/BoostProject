package kr.or.connect.project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.connect.project3.dto.ReservePage;
import kr.or.connect.project3.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	private ReserveService reserveService;
	@GetMapping("/reserve/{displayId}")
	public String reservePage(@PathVariable(name="displayId") int displayId,
			ModelMap model) throws JsonProcessingException{
		
		List<ReservePage> reservePage = reserveService.getReservePage(displayId);

		model.addAttribute("reservePage", reservePage);
		return "reserve";
	}
}

