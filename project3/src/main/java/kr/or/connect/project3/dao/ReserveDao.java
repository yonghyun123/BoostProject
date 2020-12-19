package kr.or.connect.project3.dao;

import static kr.or.connect.project3.dao.ReserveDaoSql.GET_PRICE_INFO;
import static kr.or.connect.project3.dao.ReserveDaoSql.GET_RESERVE_IMAGE;
import static kr.or.connect.project3.dao.ReserveDaoSql.GET_RESERVE_IMAGE_BY_PRODUCT_ID;
import static kr.or.connect.project3.dao.ReserveDaoSql.GET_RESERVE_PAGE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.PriceInfo;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReservationInfoData;
import kr.or.connect.project3.dto.ReservationInfoPriceData;
import kr.or.connect.project3.dto.ReservePage;


@Repository
public class ReserveDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservePage> reservePageMapper = BeanPropertyRowMapper.newInstance(ReservePage.class);
	private RowMapper<ProductType> ProductTypeMapper = BeanPropertyRowMapper.newInstance(ProductType.class);
	private RowMapper<PriceInfo> priceInfoMapper = BeanPropertyRowMapper.newInstance(PriceInfo.class);
	private SimpleJdbcInsert insertAction;
	
	public ReserveDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
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
	/** produdctId를 통해 상품에 대한 가격정보를 select하는 쿼리 */
	public List<PriceInfo> getPriceInfo(int productId){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		return jdbc.query(GET_PRICE_INFO, params, priceInfoMapper);
	}
	/** 예약자 정보를 등록하는 쿼리 */
	public Long insertReserveInfo(ReservationInfoData reservationInfoData){
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoData);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	

	
}
