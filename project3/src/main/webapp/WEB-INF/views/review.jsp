<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<html>

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>

<body>
    <div id="container">
        <!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
        <div class="header fade">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="./mainpage.html" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./mainpage.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <div class="ct">
            <div class="ct_wrap">
                <div class="top_title">
                    <a href="javascript:history.back()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                    <h2><span class="title"></span></h2>
                </div>
            </div>
        </div>
        <c:set var="sum" value="0"/>
				<c:set var="productId" value="0"/>
				<c:forEach items="${reviewJstl }" var="item">
					<c:set var="sum" value="${item.score + sum}"></c:set>
					<c:set var="productId" value="${item.productId }"/>
				</c:forEach>
		<c:set var="idx" value="${fn:length(reviewJstl) }"/>

        <div class="section_review_list">
                <div class="review_box">
                    <h3 class="title_h3">예매자 한줄평</h3>
                    <div class="short_review_area">
                        <div class="grade_area">
                            <!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
                            <span class="graph_mask"> <em class="graph_value" style="width: ${scoreAvg * 20}%"></em></span> 
								<strong class="text_value"> 
									<span><fmt:formatNumber value="${scoreAvg }"></fmt:formatNumber></span> 
									<em class="total">5.0</em>
								</strong> <span class="join_count"><em class="green">${idx}</em>건 등록</span>
                        </div>
                        <ul class="list_short_review">
                        <c:forEach items="${reviewJstl}" var="item">
					        <li class="list_item">
                                <div>
                                    <div class="review_area">
                                        <div class="thumb_area">
                                            <a href="#" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="http://naverbooking.phinf.naver.net/20170306_3/1488772023601A4195_JPEG/image.jpg?type=f300_300" alt="리뷰이미지"> </a> <span class="img_count" style="display:none;">1</span>                                                </div>
                                        <h4 class="resoc_name">${item.description }</h4>
                                        <p class="review">${item.comment }</p>
                                    </div>
                                    <div class="info_area">
                                        <div class="review_info"> <span class="grade">${item.score }</span> <span class="name">${item.reservationName }</span> 
                                        <span class="date">${item.createDate }</span> </div>
                                    </div>
                                </div>
                            </li>
						</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
    </div>
    <script>
		var nameHtml = document.querySelectorAll('.name');
		nameHtml.forEach(function(v){
			v.innerText = maskingName(v.innerText);
		});
		
		function maskingName(strCar) {
		    if (strCar == undefined || strCar === '') {
		        return '';
		    }
		    var pattern = /.$/; // 정규식
		    return strCar.replace(pattern, "*");
		}
	
	</script>
</body>

</html>

	