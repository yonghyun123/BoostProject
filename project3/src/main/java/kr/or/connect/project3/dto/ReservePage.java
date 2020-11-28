package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservePage {
	private int productId;
	private int displayId;
	private String openingHours;
	private String placeName;
}
