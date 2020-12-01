package kr.or.connect.project3.dao;

public class ReserveDaoSql {
	public static final String GET_RESERVE_PAGE = "SELECT T1.id displayId, T1.product_id productId, T1.opening_hours openingHours, T1.place_name placeName "
			+ "FROM display_info T1 "
			+ "WHERE T1.id = :displayId ";
	
	public static final String GET_RESERVE_IMAGE = "SELECT p.id id, description, content, pi.id image_id, pi.type type, pi.file_id file_id "
			+ "FROM product p, product_image pi "
			+ "WHERE p.id = :productId and pi.product_id = :productId and pi.type='ma' ";
	
	public static final String GET_RESERVE_IMAGE_BY_PRODUCT_ID = "SELECT file_name "
			+ "FROM product_image T1, file_info T2 "
			+ "WHERE T1.file_id = t2.id "
			+ "AND T1.id = :imageId ";
	
	public static final String GET_PRICE_INFO = "SELECT T1.product_id productId, T1.product_id productId, T1.price_type_name priceTypeName, T1.price price, T1.discount_rate discountRate "
			+ "FROM product_price T1 "
			+ "WHERE T1.product_id = :productId ";
}
