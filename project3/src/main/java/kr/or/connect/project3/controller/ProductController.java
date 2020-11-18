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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import kr.or.connect.project3.dto.Product;
import kr.or.connect.project3.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ServletContext servletContext;
	@Value("${image.path}")
	private String imagePath;
	
	//get product info as category id + product number
	@GetMapping("/products")
	public Map<String,Object> getProductsByCategoryId(
			@RequestParam(value="categoryId", required = false) Integer categoryId, 
			@RequestParam(value="start", required = false) Integer start)
	{
		List<Product> productList = null;
		if(categoryId == null && start == null){
			productList = productService.getProducts();
		} else if(categoryId == null && start != null){
			productList = productService.getProducts(start);
		} else {
			productList = productService.getByCategoryId(categoryId, start);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("items", productList);
		
		return map;	
	}
	
	@GetMapping("/products/{id}")
	public Map<String, Object> getProductById(@PathVariable(name="id") int id){
		List<Product> productTotalList = productService.getById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("items", productTotalList);
		return map;
	}
	
	@GetMapping(value = "/products/productImages/{id}")
	@ResponseBody
	public  ResponseEntity<Resource> getImage(
			@PathVariable(name="id") Integer id,
			@RequestParam(value="type") String type) throws IOException {
		
		String imageName = productService.getProductImageById(id, type);
		String fullPath = imagePath+imageName;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		Resource resource = new ServletContextResource(servletContext, fullPath);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
}
