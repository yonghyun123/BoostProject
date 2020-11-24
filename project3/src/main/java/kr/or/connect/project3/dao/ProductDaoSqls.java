package kr.or.connect.project3.dao;

public class ProductDaoSqls {
	public static final int numOfProduct = 4;
	public static final String SELECT_ALL_START = "SELECT product_id id, category_id, description, content, place_name, display_info.id display_id "
			+ "FROM product, display_info "
			+ "WHERE product.id = display_info.product_id "
			+ "limit :start, :limit";
	
	public static final String SELECT_BY_CATEGORY_ID = "SELECT product_id id, category_id, description, content, place_name, display_info.id display_id "
			+ "FROM product, display_info "
			+ "WHERE category_id = :categoryId and product.id = display_info.product_id "
			+ "limit :start, :limit";
	
	public static final String SELECT_ALL = "SELECT id, category_id, description, content, event, create_date, modify_date "
			+ "FROM product";
	
	public static final String SELECT_BY_ID = "SELECT id, category_id, description, content, event, create_date, modify_date "
			+ "FROM product "
			+ "WHERE id = :productId";
	
	public static final String GET_IMAGE_BY_PRODUCT_ID = "SELECT file_name "
			+ "FROM file_info "
			+ "WHERE id = "
			+ "(SELECT file_id "
			+ "FROM product_image "
			+ "WHERE product_id = :productId and type='th' "
			+ ")";
	
	public static final String GET_COUNT = "SELECT count(*) "
			+ "FROM product";
	
	public static final String GET_COUNT_BY_CATEGORY_ID = "SELECT count(*) "
			+ "FROM product "
			+ "WHERE product.category_id = :categoryId";
	
}
