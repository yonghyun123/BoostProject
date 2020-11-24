package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductType {
	private int id;
	private String description;
	private String content;
	private int imageId;
	private String type;
	private String fileId;
}
