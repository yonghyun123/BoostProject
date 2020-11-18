package kr.or.connect.project3.dao;

import static kr.or.connect.project3.dao.ProductDaoSqls.SELECT_ALL;
import static kr.or.connect.project3.dao.ProductDaoSqls.SELECT_ALL_START;
import static kr.or.connect.project3.dao.ProductDaoSqls.SELECT_BY_CATEGORY_ID;
import static kr.or.connect.project3.dao.ProductDaoSqls.SELECT_BY_ID;

import static kr.or.connect.project3.dao.ProductDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
					.withTableName("product");
	}
	
	//all get product
	public List<Product> getProducts(){
		return jdbc.query(SELECT_ALL, rowMapper);
	}

	//get four product
	public List<Product> getProducts(int start){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", numOfProduct);
		return jdbc.query(SELECT_ALL_START, params, rowMapper);
	}
	
	//get by categoryId
	public List<Product> getByCategoryId(int categoryId, int start){
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", numOfProduct);
		return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
	}
	
	//get by productId
	public List<Product> getById(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", id);
		return jdbc.query(SELECT_BY_ID, params, rowMapper);
	}
	
	//get productImage by productId
	public String getProductImageById(Integer id, String type) {
		MapSqlParameterSource mSource = new MapSqlParameterSource();
		mSource.addValue("productId", id);
		mSource.addValue("type", type);
		
		return jdbc.queryForObject(GET_IMAGE_BY_PRODUCT_ID, mSource, String.class);
	}
	
	public Integer getProductCount(){
		MapSqlParameterSource mSource = new MapSqlParameterSource();
		return jdbc.queryForObject(GET_COUNT, mSource, Integer.class);
	}
	
	public Integer getProductCntById(Integer id){
		MapSqlParameterSource mSource = new MapSqlParameterSource();
		mSource.addValue("categoryId", id);
		return jdbc.queryForObject(GET_COUNT_BY_CATEGORY_ID, mSource, Integer.class);
	}
}
