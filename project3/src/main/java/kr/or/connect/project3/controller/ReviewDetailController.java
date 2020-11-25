package kr.or.connect.project3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.project3.dto.ReviewInfo;
import kr.or.connect.project3.service.ProductDetailService;

@Controller
public class ReviewDetailController {
	@Autowired
	private ProductDetailService productDetailService;
	@GetMapping("review/{id}")
	public String getProductById(@PathVariable(name="id") int id,
								 ModelMap model) throws JsonProcessingException, ParseException{
		List<ReviewInfo> reviewList = productDetailService.getReview(id);
		
		
		for(ReviewInfo item : reviewList){
			Date temp;
			try {
				temp = new SimpleDateFormat("yyyy-MM-dd").parse(item.getCreateDate());
				item.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(temp));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("reviewList", mapper.writeValueAsString(reviewList));
		model.addAttribute("reviewJstl", reviewList);
		
		return "review";
	}
}
