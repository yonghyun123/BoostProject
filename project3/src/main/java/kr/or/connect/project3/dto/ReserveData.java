package kr.or.connect.project3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReserveData {
	private int id;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private List<PriceCountObject> priceList;
}
