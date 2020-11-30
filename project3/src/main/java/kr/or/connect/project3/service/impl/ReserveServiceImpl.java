package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.ReserveDao;
import kr.or.connect.project3.dto.ReservePage;
import kr.or.connect.project3.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService{

	
	@Autowired
	private ReserveDao reserveDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<ReservePage> getReservePage(int id) {
		List<ReservePage> list = reserveDao.getReservePage(id);
		return list;
	}

}