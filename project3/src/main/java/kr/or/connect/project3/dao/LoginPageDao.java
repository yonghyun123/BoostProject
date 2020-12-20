package kr.or.connect.project3.dao;


import static kr.or.connect.project3.dao.LoginPageDaoSqls.GET_EMAIL_INFO;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginPageDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public LoginPageDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//Session에 담을 이메일 정보 가져오기
	public String getEmailInfo(String email){
		Map<String, String> params = new HashMap<>();
		params.put("reservation_email", email);
		try{
			return jdbc.queryForObject(GET_EMAIL_INFO, params, String.class);
		} catch(EmptyResultDataAccessException e){
			return "null";
		}		
	}
}
