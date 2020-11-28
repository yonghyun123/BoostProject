package kr.or.connect.project3.dao;

public class ReserveDaoSql {
	public static final String GET_RESERVE_PAGE = "SELECT T1.id displayId, T1.product_id productId, T1.opening_hours openingHours, T1.place_name placeName "
			+ "FROM display_info T1 "
			+ "WHERE T1.id = :displayId ";
	
}
