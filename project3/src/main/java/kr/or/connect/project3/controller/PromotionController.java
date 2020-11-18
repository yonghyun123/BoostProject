package kr.or.connect.project3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import kr.or.connect.project3.dto.Promotion;
import kr.or.connect.project3.service.PromotionService;

@RestController
public class PromotionController {
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private ServletContext servletContext;
	@Value("${image.path}")
	private String imagePath;
	
	@GetMapping("/promotions")
	public Map<String, Object> getPromotions(){
		List<Promotion> promotionList = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		map.put("items", promotionList);
		return map;
	}
	
	@GetMapping("/promotions/{id}")
	@ResponseBody
	ResponseEntity<Resource> getPromotionImage(
			@PathVariable("id") Integer id ){
		
		String imageName = promotionService.getPromotionImage(id);
		String fullPath = imagePath + imageName;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		Resource resource = new ServletContextResource(servletContext, fullPath);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		
	}
}
