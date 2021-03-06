package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.DisplayInfo;
import kr.or.connect.project3.dto.DisplayType;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReviewInfo;

public interface ProductDetailService {
	public List<DisplayType> getDisplayType(int id, int displayId);
	public List<ProductType> getProductDetail(int id);
	public String getProductImageById(Integer imageId, String type);
	public List<DisplayInfo> getLocationInfoById(int displayId);
	public String getMapImageById(int displayId);
	public List<ReviewInfo> getReview(int id);
}
