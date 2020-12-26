package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.MyPageInfo;
import kr.or.connect.project3.dto.ReservedPriceinfo;

public interface MyPageInfoService {
	public List<MyPageInfo> getMyPageInfo(String email);
}
