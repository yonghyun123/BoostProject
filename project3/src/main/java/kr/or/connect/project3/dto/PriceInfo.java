package kr.or.connect.project3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PriceInfo {
	int productId;
	String priceTypeName;
	String price;
	String discountRate;
}
