package kr.or.connect.project3.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.MyPageInfoDao;
import kr.or.connect.project3.dto.MyPageInfo;
import kr.or.connect.project3.dto.ReservedPriceinfo;
import kr.or.connect.project3.service.MyPageInfoService;

@Service
public class MyPageInfoServiceImpl implements MyPageInfoService {
	
	@Autowired
	MyPageInfoDao myPageInfoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<MyPageInfo> getMyPageInfo(String email) {
		List<MyPageInfo> myPageList = myPageInfoDao.getMyPageInfo(email);
		List<ReservedPriceinfo> myPriceList = myPageInfoDao.getMyPriceInfo(email);
		int totalPrice = 0;
		for(ReservedPriceinfo items: myPriceList){
			 totalPrice += items.getCount() * items.getPrice();
		}
		String convertPrice = String.valueOf(totalPrice);
		myPageList.get(0).setTotalPrice(convertPrice);
		
		
		return myPageList;
	}

	@Override
	@Transactional
	public String deleteReserveInfo(int id) {
		String returnStr = null;
		myPageInfoDao.deleteReserveInf(id);
			
		return returnStr;
	}


}
