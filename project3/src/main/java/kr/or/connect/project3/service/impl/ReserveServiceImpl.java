package kr.or.connect.project3.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.project3.dao.ReserveDao;
import kr.or.connect.project3.dao.ReservePriceDao;
import kr.or.connect.project3.dto.PriceCountObject;
import kr.or.connect.project3.dto.PriceInfo;
import kr.or.connect.project3.dto.ProductType;
import kr.or.connect.project3.dto.ReservationInfoData;
import kr.or.connect.project3.dto.ReservationInfoPriceData;
import kr.or.connect.project3.dto.ReserveData;
import kr.or.connect.project3.dto.ReservePage;
import kr.or.connect.project3.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService{

	
	@Autowired
	private ReserveDao reserveDao;
	
	@Autowired
	private ReservePriceDao reservePriceDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<ReservePage> getReservePage(int id) {
		List<ReservePage> list = reserveDao.getReservePage(id);
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public ProductType getReserveImageById(int id) {
		ProductType reserveImage = reserveDao.getReserveImageById(id);
		return reserveImage;
	}

	@Override
	@Transactional(readOnly=true)
	public String getProductReserveImageById(int imageId) {
		String imageName = reserveDao.getProductReserveImageById(imageId);
		return imageName;
	}

	@Override
	public List<PriceInfo> getPriceInfo(int productId) {
		List<PriceInfo> list = reserveDao.getPriceInfo(productId);
		for(PriceInfo item: list){
			if("A".equals(item.getPriceTypeName())){
				item.setTypeAge("성인");
			} else if("Y".equals(item.getPriceTypeName())){
				item.setTypeAge("청소년");
			} else if("B".equals(item.getPriceTypeName())){
				item.setTypeAge("유아");
			} else{
				item.setTypeAge("미취학");
			}
		}
		return list;
	
	}

	@Override
	@Transactional(readOnly=false)
	public String insertPriceData(ReserveData reserveData) {
		
		ReservationInfoData reservationInfo =  new ReservationInfoData();
		reservationInfo.setCancelFlag(0);
		reservationInfo.setCreateDate(new Date());
		reservationInfo.setModifyDate(new Date());
		reservationInfo.setReservationDate(new Date());
		reservationInfo.setProductId(reserveData.getProductId());
		reservationInfo.setDisplayInfoId(reserveData.getDisplayInfoId());
		reservationInfo.setReservationName(reserveData.getReservationName());
		reservationInfo.setReservationEmail(reserveData.getReservationEmail());
		reservationInfo.setReservationTel(reserveData.getReservationTel());
		
		Long reservationInfoId = reserveDao.insertReserveInfo(reservationInfo);
		
		
		for(PriceCountObject item: reserveData.getPriceList()){
			ReservationInfoPriceData reservationInfoPriceData = new ReservationInfoPriceData();
			reservationInfoPriceData.setReservationInfoId(reservationInfoId);
			reservationInfoPriceData.setCount(item.getCount());
			reservationInfoPriceData.setProductPriceId(item.getPriceId());
			reservePriceDao.insertReservationInfoPrice(reservationInfoPriceData);
		}

		return "Success";
	}

}
