// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {
	var productId = document.querySelector("#productId").value;
	var imageTypeUrl = "/project3/products/detail/" + productId;
	
	detailImageAjax(imageTypeUrl);
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
	var contentHtml = document.querySelector('#intro-content').innerText;
	bindTemplate = Handlebars.compile(introHtml);
	var explainHtml = document.querySelector('.explain-item');
	explainHtml.innerHTML = bindTemplate(json['imageTypeList'][0]);
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