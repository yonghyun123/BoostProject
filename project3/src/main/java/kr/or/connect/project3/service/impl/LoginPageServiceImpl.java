package kr.or.connect.project3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.LoginPageDao;
import kr.or.connect.project3.service.LoginPageService;

@Service
public class LoginPageServiceImpl implements LoginPageService {

	@Autowired
	private LoginPageDao loginPageDao;
	
	@Override
	@Transactional(readOnly=true)
	public String getEmailInfo(String reserveEamil) {
		return loginPageDao.getEmailInfo(reserveEamil);
	}

}
