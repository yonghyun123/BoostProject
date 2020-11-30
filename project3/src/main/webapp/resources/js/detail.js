// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {
	var productId = document.querySelector("#productId").value;
	var imageTypeUrl = "/project3/products/detail/" + productId;
	var reviewUrl = "/project3/products/detail/review/" + productId;
	
	detailImageAjax(imageTypeUrl);
	openClose(); //접기 펼치기 기능
	DetailTab.registerEvents(); //객체리터럴을 이용한 TabUI 구현 
	reviewAjax(reviewUrl); //detail 페이지에서 리뷰를 가져오기 위한 ajax
}

//get promotion image, content, description
function detailImageAjax(url) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.send();
	var pmJson = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				pmJson = JSON.parse(xhr.responseText);
				makeTitle(pmJson);  
				makeExplain(pmJson);
				
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

/** 핸들바를 이용한 데이터 바인딩 (연극 content) */
function makeExplain(json) {
	var introHtml = document.querySelector('#intro-item').innerText;
	bindTemplate = Handlebars.compile(introHtml);
	var explainHtml = document.querySelector('.explain-item');
	explainHtml.innerHTML = bindTemplate(json['imageTypeList'][0]);
	
	var contentHtml = document.querySelector('#intro-content').innerText;
	bindTemplate = Handlebars.compile(contentHtml);
	document.querySelector('#main-content').innerHTML = bindTemplate(json['imageTypeList'][0]);		
}

//타입에 맞는 이미지를 분기로 나눠서 setting
function makeTitle(json) {		
	for (key in json) {
		var cnt = 0;
		var resultHtml = '';
		var originHtml = document.querySelector('#itemInfo-list').innerHTML;
		
		
		for(idx in json[key]){
			if (json[key][idx].type === 'ma') {
				resultHtml += originHtml.replace('{imageId}', json[key][idx].imageId)
						.replace('{description}', json[key][idx].description);
				cnt++;
			}
			if (json[key][idx].type === 'et') {
				resultHtml += originHtml.replace('{imageId}', json[key][idx].imageId)
						.replace('{description}', json[key][idx].description);
				cnt++;
			}
			if (cnt >= 2) {
				break;
			}
			
		}
		if (cnt >= 2) {
			break;
		}
	}
	var parentHtml = document.querySelector('.visual_img');
	parentHtml.innerHTML = resultHtml;
	initImgEvent();
	imgCntButton();
	imageSlider();
	
}

//image slider를 위한 이미지 클론 후 setting
function initImgEvent() {
	var parent = document.querySelector('.visual_img').lastElementChild;
	var copyChild = parent.cloneNode(true);
	document.querySelector('.visual_img').appendChild(copyChild);
	var addCss = document.querySelectorAll('.add_duration');
	addCss.forEach(function(v, i) {
		v.style.transitionDuration = '0.3s'
		v.style.position = 'absolute';
		if (i == 0) {
			v.id = 'item' + i;
			v.style.left = '0px';
		} else if (i == 1) {
			v.id = 'item' + i;
			v.style.left = '414px';
		} else if (i == 2) {
			v.id = 'item' + i;
			v.style.left = '-414px';
		}
	})
}

/**이미지가 슬라이딩 할 때마다 cnt를 바꿔주는 함수 */
function imgCntButton() {
	var imgCnt = document.querySelectorAll('.add_duration');
	var imgLen = imgCnt.length;
	var leftRightImg = document.querySelectorAll('.right_left_group');
	if (imgLen == 2) {
		leftRightImg.forEach(function(v) {
			v.classList.remove('spr_book2');
		})
		document.getElementById('total-img').innerText = 1;
	} else if (imgLen > 2) {
		leftRightImg.forEach(function(v) {
			v.classList.add('spr_book2');
			v.classList.remove('off');
		})
		document.getElementById('total-img').innerText = 2;
	}
}


//imageSlider main function
function imageSlider(){
	var imgItems = document.querySelectorAll('.add_duration');
	// 오른쪽 button slide
	if (imgItems.length >= 3) {
		var rigthBtn = document.querySelector('.nxt');
		rigthBtn.addEventListener('click', function(evt) {
			flipFunc();
			var centerB = document.getElementById('item0');
			var rightB = document.getElementById('item1');
			var leftB = document.getElementById('item2');
			var copyCenter = centerB.cloneNode(true);
			leftB.remove();

			// left가 됨
			centerB.style.left = '-414px';
			centerB.id = 'item2';
			// center가 됨
			rightB.style.left = '0px';
			rightB.id = 'item0';
			// rigth가 됨
			copyCenter.id = 'item1';
			copyCenter.style.left = '414px';
			document.querySelector('.visual_img').appendChild(copyCenter);
		})
		// 왼쪽 button slide
		var leftBtn = document.querySelector('.prev');
		leftBtn.addEventListener('click', function(evt) {
			flipFunc();
			var centerB = document.getElementById('item0');
			var rightB = document.getElementById('item1');
			var leftB = document.getElementById('item2');
			var copyCenter = centerB.cloneNode(true);
			rightB.remove();

			// right가 됨
			centerB.style.left = '414px';
			centerB.id = 'item1';
			// center가 됨
			leftB.style.left = '0px';
			leftB.id = 'item0';

			// rigth가 됨
			copyCenter.id = 'item2';
			copyCenter.style.left = '-414px';
			document.querySelector('.visual_img').appendChild(copyCenter);

		});
	}
}

//image가 바뀔때마다 이미지 번호를 보여주기 위한 함수
function flipFunc() {
	var curCnt = document.getElementById('cur-img');
	if (curCnt.innerText != 1) {
		curCnt.innerText = 1;
	} else if (curCnt.innerText != 2) {
		curCnt.innerText = 2;
	}
}


//펼쳐보기 기능 
function openClose() {
	var bkMoreOpen = document.querySelector('#open');
	if (bkMoreOpen.classList.contains('_open')) {
		bkMoreOpen.addEventListener('click', function(e) {
			var detailEl = document.querySelector('.store_details');
			detailEl.classList.remove('close3');
			bkMoreOpen.style.display = 'none';

			var closeEl = document.querySelector('#close');
			closeEl.style.display = 'block';

		});
	}

	var bkMoreClose = document.querySelector('#close');
	if (bkMoreClose.classList.contains('_close')) {
		bkMoreClose.addEventListener('click', function(e) {
			var detailEl = document.querySelector('.store_details');
			detailEl.classList.add('close3');
			bkMoreClose.style.display = 'none';

			var openEl = document.querySelector('#open');
			openEl.style.display = 'block';
		})
	}
}

/** 해당 항목 review를 가져오기 위한 Ajax */
function reviewAjax(url){
	var xhr = new XMLHttpRequest();
	xhr.addEventListener("load", function() {
		var reviewData = JSON.parse(xhr.responseText);
		// making function
		makeReview(reviewData);
	});
	xhr.open("GET", url);
	xhr.send();
}

//해당 상품에 대한 리뷰를 html handlebar를 이용해 바인딩
function makeReview(reviewJson){
	var reviewHtml = document.querySelector('#review-item').innerHTML;
	var bindTemplate = Handlebars.compile(reviewHtml);
	var explainHtml = document.querySelector('.list_short_review');
	var newHtml = '';

	reviewJson['reviewInfo'].forEach(function(v){
		v.score = Number(v.score).toFixed(1);
		newHtml += bindTemplate(v);
	})

	explainHtml.innerHTML = newHtml;
}