package kr.or.connect.project3.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.connect.project3.dto.DisplayType;
import kr.or.connect.project3.service.ProductDetailService;
import kr.or.connect.project3.util.Util;

@Controller
public class ProductDetailController {


	@Autowired
	private ProductDetailService productDetailService;
	
	@Autowired
	private ServletContext servletContext;
	@Value("${image.path}")
	private String imagePath;
	@Value("${image.map.path}")
	private String imageMapPath;
	
	/* 처음 productId와 displayId 받아와서 페이지 렌더링 컨트롤러*/
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable(name="id") int id,
								 @RequestParam (value="displayId") int displayId,
								 ModelMap model) throws JsonProcessingException{
		List<DisplayType> detailList = productDetailService.getDisplayType(id, displayId);
		for(DisplayType item : detailList){
			item.setContent(Util.escapeJson(item.getContent()));
			item.setDescription(Util.escapeJson(item.getDescription()));
		}		
		model.addAttribute("detailList", detailList);
		return "detail";
	}
}
