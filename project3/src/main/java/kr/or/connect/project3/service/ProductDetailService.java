package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.DisplayType;

public interface ProductDetailService {
	public List<DisplayType> getDisplayType(int id, int displayId);
}
