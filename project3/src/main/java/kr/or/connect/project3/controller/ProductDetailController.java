package kr.or.connect.project3.controller;

import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.connect.project3.dto.DisplayType;
import kr.or.connect.project3.dto.ProductType;
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
	
	/* productId를 이용해 이미지type을 응답하기 위한 컨트롤러 */
	@GetMapping("/products/detail/{id}")
	@ResponseBody
	public Map<String, Object> getProductById(@PathVariable(name="id") int id){
		List<ProductType> imageTypeList = productDetailService.getProductDetail(id);
		Map<String, Object> imageTypeMap = new HashMap<>();
		imageTypeMap.put("imageTypeList", imageTypeList);
		return imageTypeMap;
	}
	
	/* 해당 상품에 이미지를 보여주기 위한 컨트롤러(detail page main image) */
	@GetMapping(value = "/products/detailImage/{imageid}")
	@ResponseBody
	public  ResponseEntity<Resource> getImage(
			@PathVariable(name="imageid") Integer imageId,
			@RequestParam(value="type",required = false) String type) throws IOException {
		String imagePath = "resources/img/";
		String imageName = productDetailService.getProductImageById(imageId, type);
		String fullPath = imagePath+imageName;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		Resource resource = new ServletContextResource(servletContext, fullPath);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
