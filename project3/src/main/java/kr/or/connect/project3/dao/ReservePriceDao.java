package kr.or.connect.project3.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.project3.dto.ReservationInfoPriceData;

@Repository
public class ReservePriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ReservePriceDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}
	
	/**예약자 정보에 대한 티켓 수, 티켓 가격을 등록하는 쿼리 */
	public Long insertReservationInfoPrice(ReservationInfoPriceData reservationInfoPriceData){
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPriceData);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
