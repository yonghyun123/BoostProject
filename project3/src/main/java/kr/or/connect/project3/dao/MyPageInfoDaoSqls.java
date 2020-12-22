package kr.or.connect.project3.dao;

public class MyPageInfoDaoSqls {
	public static final String SELETE_MY_PAGE = "SELECT t1.product_id, t1.display_info_id, t2.description, t3.opening_hours, t3.place_street, t1.cancel_flag "
			+ "FROM reservation_info t1, product t2, display_info t3 "
			+ "where t1.product_id = t2.id "
			+ "and t1.display_info_id = t3.id "
			+ "and t1.reservation_email = :reservation_email";
			
}
