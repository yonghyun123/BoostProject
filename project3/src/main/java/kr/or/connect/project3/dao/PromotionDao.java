package kr.or.connect.project3.dao;

import static kr.or.connect.project3.dao.PromotionDaoSql.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
					.withTableName("product");
	}
	
	public List<Promotion> getPromotions(){
		return jdbc.query(SELECT_ALL, rowMapper);
	}
	
	public String getPromotionImage(int id){
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		return jdbc.queryForObject(GET_PROMOTION_IMAGE, map, String.class);
	}
}
