package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationInfoPriceData {
	private int id;
	private Long reservationInfoId;
	private int productPriceId;
	private int count;
}
