package kr.or.connect.project3.dao;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT c.id, c.name, count(c.id) as count "
			+ "FROM category c, product p, display_info di "
			+ "WHERE c.id = p.category_id and p.id = di.product_id "
			+ "group by c.id";
	
}
