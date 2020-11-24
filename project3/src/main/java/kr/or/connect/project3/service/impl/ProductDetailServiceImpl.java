package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.ProductDetailDao;
import kr.or.connect.project3.dto.DisplayInfo;
import kr.or.connect.project3.dto.DisplayType;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReviewInfo;
import kr.or.connect.project3.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{

	@Autowired
	private ProductDetailDao productDetailDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<DisplayType> getDisplayType(int id, int displayId) {
		List<DisplayType> list = productDetailDao.getDisplayType(id, displayId);
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProductType> getProductDetail(int id) {
		List<ProductType> imageTypeList = productDetailDao.getProductDetail(id);
		return imageTypeList;
	}

	@Override
	@Transactional(readOnly=true)
	public String getProductImageById(Integer imageId, String type) {
		String imageName = productDetailDao.getProductImageById(imageId, type);
		return imageName;
	
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<DisplayInfo> getLocationInfoById(int displayId) {
		List<DisplayInfo> list = productDetailDao.getLocationInfoById(displayId);
		return list;
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getMapImageById(int displayId) {
		String imageName = productDetailDao.getMapImage(displayId);
		return imageName;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ReviewInfo> getReview(int id) {
		List<ReviewInfo> list = productDetailDao.getReview(id);
		return list;
	}

	
}
