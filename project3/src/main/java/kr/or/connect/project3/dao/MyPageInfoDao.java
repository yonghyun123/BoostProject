package kr.or.connect.project3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.MyPageInfo;
import kr.or.connect.project3.dto.ReservedPriceinfo;

import static kr.or.connect.project3.dao.MyPageInfoDaoSqls.*;

@Repository
public class MyPageInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MyPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(MyPageInfo.class);
	private RowMapper<ReservedPriceinfo> priceInfoMapper = BeanPropertyRowMapper.newInstance(ReservedPriceinfo.class);
	
	public MyPageInfoDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<MyPageInfo> getMyPageInfo(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("reservation_email", email);
		
		return jdbc.query(SELETE_MY_PAGE, params, rowMapper);
	}
	
	public List<ReservedPriceinfo> getMyPriceInfo(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("reservation_email", email);
		return jdbc.query(SELECT_PRICE_INFO, params, priceInfoMapper);
	}
}
