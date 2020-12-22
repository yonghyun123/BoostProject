package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.MyPageInfo;

public interface MyPageInfoService {
	public List<MyPageInfo> getMyPageInfo(String email);
}
