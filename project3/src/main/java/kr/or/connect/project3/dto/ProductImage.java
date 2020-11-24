package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductImage {
	private int id;
	private int productId;
	private String type;
	private int fileId;
}
