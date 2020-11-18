package kr.or.connect.project3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.project3.dto.Category;
import kr.or.connect.project3.service.CategoryService;

@RestController
@RequestMapping(path="/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public Map<String, Object> categoryList(){
		
		List<Category> categoryList = categoryService.getCategories();
		Map<String, Object> categoryMap = new HashMap<>();
		int totalProduct = 0;
		categoryMap.put("items", categoryList);
		for(int i = 0; i < categoryList.size(); i++){
			totalProduct += categoryList.get(i).getCount();
		}
		categoryMap.put("size", totalProduct);
		return categoryMap;
		

	}

}
