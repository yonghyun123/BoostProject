package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DisplayInfo {
	private int id;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String tel;
	private String description;
}
