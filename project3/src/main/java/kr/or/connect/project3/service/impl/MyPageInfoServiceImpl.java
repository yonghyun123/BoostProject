package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.MyPageInfoDao;
import kr.or.connect.project3.dto.MyPageInfo;
import kr.or.connect.project3.service.MyPageInfoService;

@Service
public class MyPageInfoServiceImpl implements MyPageInfoService {
	
	@Autowired
	MyPageInfoDao myPageInfoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<MyPageInfo> getMyPageInfo(String email) {
		List<MyPageInfo> myPageList = myPageInfoDao.getMyPageInfo(email);
		return myPageList;
	}

}
