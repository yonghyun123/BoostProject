package kr.or.connect.project3.dao;

import static kr.or.connect.project3.dao.ProductDetailDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.DisplayType;


@Repository
public class ProductDetailDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayType> rowMapper = BeanPropertyRowMapper.newInstance(DisplayType.class);
	
	public ProductDetailDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
					.withTableName("ProductType");
	}
	
	/** 처음 productId와 displayInfoId를 select하는 쿼리 */
	public List<DisplayType> getDisplayType(int id, int displayId){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", id);
		params.put("displayId", displayId);
		return jdbc.query(GET_PRODUCT_TYPE, params, rowMapper);
	}
	
	
}
