package kr.or.connect.project3.dao;

public class MyPageInfoDaoSqls {
	public static final String SELETE_MY_PAGE = "SELECT t1.id id, t1.product_id, t1.display_info_id, t2.description, t3.opening_hours, t3.place_street, t1.cancel_flag "
			+ "FROM reservation_info t1, product t2, display_info t3 "
			+ "where t1.product_id = t2.id "
			+ "and t1.display_info_id = t3.id "
			+ "and t1.reservation_email = :reservation_email";
	
	public static final String SELECT_PRICE_INFO = "SELECT T3.ID, T2.COUNT, T3.PRICE "
			+ "FROM reservation_info T1, reservation_info_price T2, product_price T3 "
			+ "where T1.id = T2.reservation_info_id "
			+ "and T2.product_price_id = T3.id "
			+ "and T1.reservation_email = :reservation_email";
	
	public static final String DELETE_BY_ID = "UPDATE FROM reservation_info SET cancel_flag = 1 WHERE id =: reservation_info_id";
	
}
