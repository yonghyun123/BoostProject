package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewInfo {
	private int id; //reservation_info id
	private String reservationName;
	private int productId;
	private float score;
	private String comment;
	private String createDate;
	private String description;
}
