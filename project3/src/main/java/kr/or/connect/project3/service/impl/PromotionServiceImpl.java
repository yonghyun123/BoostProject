package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.PromotionDao;
import kr.or.connect.project3.dto.Promotion;
import kr.or.connect.project3.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Promotion> getPromotions() {
		List<Promotion> promotionList = promotionDao.getPromotions();
		return promotionList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getPromotionImage(Integer id){
		String imageName = promotionDao.getPromotionImage(id);
		return imageName;
	}

}
