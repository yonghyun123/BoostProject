package kr.or.connect.project3.service;

import java.util.List;

import kr.or.connect.project3.dto.PriceInfo;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReservePage;

public interface ReserveService {
	public List<ReservePage> getReservePage(int id);
	public ProductType getReserveImageById(int id);
	public String getProductReserveImageById(int imageId);
	public List<PriceInfo> getPriceInfo(int productId);
}
