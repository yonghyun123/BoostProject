package kr.or.connect.project3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.CategoryDao;
import kr.or.connect.project3.dto.Category;
import kr.or.connect.project3.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Category> getCategories() {
		List<Category> categoryList = categoryDao.getCategories();
		return categoryList;
	}
}
