package kr.or.connect.project3.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.project3.dto.MyPageInfo;
import kr.or.connect.project3.service.MyPageInfoService;

@Controller
public class MyPageInfoController {
	
	@Autowired
	MyPageInfoService myPageInfoService;
	
	@GetMapping("/mypage")
	@ResponseBody
	public Map<String,Object> getMyPageInfo(@RequestParam("email") String email){
		System.out.println(email);
		List<MyPageInfo> pageInfo = myPageInfoService.getMyPageInfo(email);
		for(MyPageInfo item: pageInfo){
			System.out.println(item.getDescription());
		}
		Map<String,Object> pageInfoObj = new HashMap<>();
		pageInfoObj.put("items", pageInfo);
		return pageInfoObj;
	}
}
