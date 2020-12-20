package kr.or.connect.project3.dao;

public class LoginPageDaoSqls {
	public static final String GET_EMAIL_INFO = "SELECT reservation_email "
			+ "FROM reservation_info "
			+ "WHERE reservation_email = :reservation_email "
			+ "limit 1";	
}
