package kr.or.connect.project3.dao;

public class PromotionDaoSql {
	public static final String SELECT_ALL = "SELECT pm.id id, pd.id product_id, c.id category_id, c.name category_name, pd.description description, pi.id product_image_id "
			+ "FROM product pd, promotion pm, product_image pi, category c "
			+ "WHERE pm.product_id = pd.id and pd.category_id = c.id and pi.product_id = pd.id and pi.type = 'ma'";
	
	public static final String GET_PROMOTION_IMAGE = " SELECT file_name "
			+ "FROM file_info "
			+ "WHERE id = "
			+ "(SELECT file_id "
			+ "FROM product_image "
			+ "WHERE id = :id "
			+ ")";
			
}
