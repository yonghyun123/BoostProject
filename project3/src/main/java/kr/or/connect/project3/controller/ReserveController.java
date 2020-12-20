package kr.or.connect.project3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.connect.project3.dto.PriceInfo;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReserveData;
import kr.or.connect.project3.dto.ReservePage;
import kr.or.connect.project3.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	private ReserveService reserveService;
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/reserve/{displayId}")
	public String reservePage(@PathVariable(name="displayId") int displayId,
			ModelMap model) throws JsonProcessingException{
		
		List<ReservePage> reservePage = reserveService.getReservePage(displayId);

		model.addAttribute("reservePage", reservePage);
		return "reserve";
	}
	
	/* productId를 이용해 thum 이미지id를 응답하기 위한 컨트롤러 */
	@GetMapping("/products/reserve/{id}")
	@ResponseBody
	public Map<String, Object> getReserveImageById(@PathVariable(name="id") int id){
		ProductType imageObj = reserveService.getReserveImageById(id);
		Map<String, Object> imageTypeMap = new HashMap<>();
		imageTypeMap.put("imageTypeList", imageObj);
		return imageTypeMap;
	}
	
	/* 해당 상품에 이미지를 보여주기 위한 컨트롤러(detail page main image) */
	@GetMapping(value = "/products/reserveImage/{imageId}")
	@ResponseBody
	public  ResponseEntity<Resource> getImage(
			@PathVariable(name="imageId") Integer imageId) throws IOException {
		String imagePath = "resources/img/";
		String imageName = reserveService.getProductReserveImageById(imageId);
		String fullPath = imagePath+imageName;
		System.out.println(fullPath);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		Resource resource = new ServletContextResource(servletContext, fullPath);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	/* productId를 이용해 price type, price 정보를 응답하기 위한 컨트롤러 */
	@GetMapping("/reserve/priceInfo/{id}")
	@ResponseBody
	public Map<String, Object> getPriceInfo(@PathVariable(name="id") int productId){
		System.out.println("in getPriceInfo");
		List<PriceInfo> priceInfoList = reserveService.getPriceInfo(productId);
		Map<String, Object> imageTypeMap = new HashMap<>();
		imageTypeMap.put("priceInforList", priceInfoList);
		return imageTypeMap;
	}
	
	@PostMapping("/reserve/insert")
	public String insertPriceData(@RequestBody ReserveData reserveData){
		
		System.out.println(reserveData.getProductId());
		System.out.println(reserveData.getReservationTel());
		System.out.println(reserveData.getPriceList().get(1).getPriceId());
		reserveService.insertPriceData(reserveData);
		
		
		return "index";
	}
}

