package kr.or.connect.project3.dao;

import static kr.or.connect.project3.dao.ReserveDaoSql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReservePage;


@Repository
public class ReserveDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservePage> reservePageMapper = BeanPropertyRowMapper.newInstance(ReservePage.class);
	private RowMapper<ProductType> ProductTypeMapper = BeanPropertyRowMapper.newInstance(ProductType.class);
	
	public ReserveDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}
	
	/** 처음 productId와 displayInfoId를 select하는 쿼리 */
	public List<ReservePage> getReservePage(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("displayId", id);
		return jdbc.query(GET_RESERVE_PAGE, params, reservePageMapper);
	}
	
	public ProductType getReserveImageById(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", id);
		return jdbc.queryForObject(GET_RESERVE_IMAGE, params, ProductTypeMapper);
	}
	
	/** 상품에 해당하는 이미지를 가져오는 쿼리 */
	public String getProductReserveImageById(Integer imageId) {
		MapSqlParameterSource mSource = new MapSqlParameterSource();
		mSource.addValue("imageId", imageId);
		return jdbc.queryForObject(GET_RESERVE_IMAGE_BY_PRODUCT_ID, mSource, String.class);
	}
}
