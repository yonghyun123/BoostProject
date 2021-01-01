package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageInfo {
	private int id;
	private int productId;
	private int displayInfoId;
	private String description;
	private String openingHours;
	private String placeStreet;
	private int cancelFlag;
	private String totalPrice;
	
}
