package kr.or.connect.project3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.connect.project3.dto.ProductType;
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
	
	/* productId를 이용해 thum 이미지를 응답하기 위한 컨트롤러 */
	@GetMapping("/products/reserve/{id}")
	@ResponseBody
	public Map<String, Object> getReserveImageById(@PathVariable(name="id") int id){
		ProductType imageObj = reserveService.getReserveImageById(id);
		Map<String, Object> imageTypeMap = new HashMap<>();
		imageTypeMap.put("imageTypeList", imageObj);
		return imageTypeMap;
	}
}

