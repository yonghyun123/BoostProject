package kr.or.connect.project3.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationInfoData {
	private int id;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationEmail;
	private String reservationTel;
	private Date reservationDate;
	private int cancelFlag;
	private Date createDate;
	private Date modifyDate;
}
