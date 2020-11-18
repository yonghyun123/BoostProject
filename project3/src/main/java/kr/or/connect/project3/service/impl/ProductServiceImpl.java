package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.ProductDao;
import kr.or.connect.project3.dto.Product;
import kr.or.connect.project3.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Product> getProducts() {
		List<Product> getAllList = productDao.getProducts();
		return getAllList;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> getByCategoryId(int categoryId, Integer start) {
		List<Product> productList = productDao.getByCategoryId(categoryId, start);
		int count = productDao.getProductCntById(categoryId);
		for(Product item : productList){
			item.setCount(count);
		}
		return productList;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> getById(int id) {
		List<Product> list = productDao.getById(id);
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> getProducts(Integer start) {
		List<Product> listByCnt = productDao.getProducts(start); 
		int count = productDao.getProductCount();
		for(Product item : listByCnt){
			item.setCount(count);
		}
		return listByCnt;
	}
	
	//해당 타입과 product id로 이미지 이름 가져오기
	@Override
	@Transactional(readOnly=true)
	public String getProductImageById(Integer id, String type) {
		String imageName = productDao.getProductImageById(id, type);
		return imageName;
	}

}
