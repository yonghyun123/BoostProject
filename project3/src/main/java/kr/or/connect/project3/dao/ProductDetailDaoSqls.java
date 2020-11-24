package kr.or.connect.project3.dao;

public class ProductDetailDaoSqls {
	
	public static final String GET_PRODUCT_DETAIL = "SELECT p.id id, description, content, pi.id image_id, pi.type type, pi.file_id file_id "
			+ "FROM product p, product_image pi "
			+ "WHERE p.id = :productId and pi.product_id = :productId and (pi.type='et' or pi.type='ma')";
	
	public static final String GET_PRODUCT_TYPE = "SELECT di.product_id productId, di.id displayId, p.description description, p.content content "
			+ "FROM product p, display_info di "
			+ "WHERE di.id = :displayId and p.id = :productId";
	
	public static final String GET_REVIEW = "SELECT ri.id, ri.reservation_name, ri.product_id, ruc.score, ruc.comment, ruc.create_date, p.description "
			+ "FROM reservation_user_comment ruc, reservation_info ri, product p "
			+ "WHERE ruc.reservation_info_id = ri.id and ri.product_id = :productId and p.id = :productId and ruc.product_id = :productId";

	public static final String GET_IMAGE_BY_PRODUCT_ID = "SELECT file_name "
			+ "FROM file_info "
			+ "WHERE id = "
			+ "(SELECT file_id "
			+ "FROM product_image "
			+ "WHERE product_image.id = :productId "
			+ ")";
	public static final String GET_REVIEW_INFO = "SELECT product_id, count(product_id) total_review, avg(score) avg_score "
			+ "FROM reservation_user_comment "
			+ "WHERE product_id = :productId "
			+ "GROUP BY product_id";
	
	public static final String GET_MAP_IMAGE_BY_ID = "SELECT file_name "
			+ "FROM file_info "
			+ "WHERE id = "
			+ "(SELECT file_id "
			+ "FROM display_info_image "
			+ "WHERE display_info_id = :displayId "
			+ ")";
			
	public static final String GET_DISPLAY_DETAIL = "SELECT di.id id, di.place_name placeName, di.place_lot placeLot, di.place_street placeStreet, di.tel tel, p.description description "
			+ "FROM display_info di, product p "
			+ "WHERE di.id = :displayId and p.id = di.product_id";
	
	public static final String GET_TYPE_BY_ID = "SELECT count(type) "
			+ "FROM product_image pi "
			+ "WHERE pi.product_id = :productId and type = :type ";
}
