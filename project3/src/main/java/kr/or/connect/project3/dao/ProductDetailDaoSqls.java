package kr.or.connect.project3.dao;

public class ProductDetailDaoSqls {
	
	public static final String GET_PRODUCT_TYPE = "SELECT di.product_id productId, di.id displayId, p.description description, p.content content "
			+ "FROM product p, display_info di "
			+ "WHERE di.id = :displayId and p.id = :productId";
}
