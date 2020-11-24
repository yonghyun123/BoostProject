package kr.or.connect.project3.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	private int id;
	private int categoryId;
	private int displayId;
	private String description;
	private String content;
	private String event;
	private LocalDate createDate;
	private LocalDate modifyDate;
	private String placeName;
	private int count;
	
	
}
