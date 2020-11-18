package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.Product;

public interface ProductService {
	public List<Product> getProducts();
	public List<Product> getProducts(Integer start);
	public List<Product> getByCategoryId(int categoryId, Integer start);
	public List<Product> getById(int id);
	public String getProductImageById(Integer id, String type);
	
}
