package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.Promotion;

public interface PromotionService {
	public List<Promotion> getPromotions();
	public String getPromotionImage(Integer id);
}
