// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})
function init() {
	var productUrl = 'products?start=';
	var promotionUrl = 'promotions'
	var start = 0;
	// ajax 초기화
	productAjax(productUrl, start);
	promotionAjax(promotionUrl);
}

// get promotion ajax
function promotionAjax(url) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.send();
	var pmJson = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				pmJson = JSON.parse(xhr.responseText);
				makePromotionList(pmJson);
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

function productAjax(url, start) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url + start, true);
	xhr.send();
	var json = null;

	// Event Handler
	xhr.onreadystatechange = function() {
		// 서버 응답 완료 && 정상 응답
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				json = JSON.parse(xhr.responseText);
				if (start == 0) {
					makeTabList(json, 0);
					addCountHtml();
				} else {
					makeTabList(json, 1);
				}
				var wrapBox = document.querySelector('.wrap_event_box');
				var eventPreviewList = document
						.querySelectorAll('.item_preview');
				wrapBox.id = eventPreviewList.length;
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	};
}
// promotion items
function makePromotionList(json) {
	var resultHtml = '';
	var addedNewHtml = '';
	var orientHtml = document.getElementById('promotionItem').innerHTML;

	for (key in json.items) {
		resultHtml = orientHtml.replace('{productImageId}',
				json.items[key].productImageId);
		addedNewHtml += resultHtml;
	}
	addPromotionHtml(addedNewHtml);
}

// product items
function makeTabList(json, flag) {
	var addedHtmlLeft = '';
	var addedHtmlRight = '';
	var html = document.getElementById('itemList').innerHTML;

	var resultHtml = '';
	var cnt = 0

	for (key in json.items) {
		resultHtml = html.replace(/{id}/gi, json.items[key].id)
						 .replace('{description}', json.items[key].description)
						 .replace('{content}', json.items[key].content)
						 .replace('{placeName}',json.items[key].placeName)
						 .replace('{displayId}',json.items[key].displayId);
		if (cnt % 2 == 0) {
			addedHtmlLeft += resultHtml;
		} else {
			addedHtmlRight += resultHtml;
		}
		cnt++;

	}

	// category clicked!!
	if (flag == 0) {
		addProductHtml(addedHtmlLeft, addedHtmlRight);
	}

	// more button clicked!!
	else {
		moreProductHtml(addedHtmlLeft, addedHtmlRight);
	}

	// 더보기에서 json 데이터가 4보다 작으면 더보기 div hide
	if (json.items.length < 4) {
		var moreButton = document.getElementById('more-btn');
		moreButton.style.display = 'none';
	} else {
		var moreButton = document.getElementById('more-btn');
		moreButton.style.display = 'block';
	}
}

// count template html
function addCountHtml() {
	var xhr = new XMLHttpRequest();
	var productsCntUrl = 'categories'
	xhr.open('GET', productsCntUrl, true);
	xhr.send();
	var json = null;
	xhr.onreadystatechange = function() {
		// 서버 응답 완료 && 정상 응답
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				json = JSON.parse(xhr.responseText);
				var activeAnchor = document.querySelectorAll('.anchor');
				var categoryId = '';

				for (var i = 0; i < activeAnchor.length; i++) {
					if (activeAnchor[i].classList.contains('active')) {
						categoryId = i;
						break;
					}
				}

				var cntHtml = document.querySelector('#itemCnt').innerHTML;
				if (categoryId == 0) {
					var convertCntHtml = cntHtml.replace('{itemCnt}', json.size
							+ '개');
				} else {

					var convertCntHtml = cntHtml.replace('{itemCnt}',
							json.items[categoryId - 1].count + '개');
				}

				var selfHtml = document.querySelector('.pink');
				selfHtml.innerHTML = convertCntHtml;
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	};

}

// make promotion images
function addPromotionHtml(newHtml) {
	var promotionHtml = document.querySelector('.visual_img');
	promotionHtml.innerHTML = newHtml;
	promotionSlide();

}

// promotion slide 
function promotionSlide() {
	var promotionList = document.querySelectorAll('.promotion_list');
	var posInterval = -414;

	promotionList.forEach(function(v, i) {
		var pos = 414;
		v.style.transitionDuration = '0.3s'
		v.style.position = 'absolute';
		v.style.left = pos * i + 'px';
	})

	setInterval(function() {

		var promotionLotate = document.querySelectorAll('.promotion_list');
		promotionLotate.forEach(function(v, i) {
			v.style.left = parseInt(v.style.left) + posInterval;

		})

		if (promotionLotate[0].style.left === '-828px') {
			var copyCenter = promotionLotate[0].cloneNode(true);
			copyCenter.style.left = "3726";
			promotionLotate[0].remove();
			document.querySelector('.visual_img').appendChild(copyCenter);
		}
	}, 1000)
}

// make moreButton listener productView
function moreProductHtml(left, right) {
	var boxHtmlLeft = document.getElementById('left_box');
	var boxHtmlRight = document.getElementById('right_box');
	boxHtmlLeft.innerHTML += left;
	boxHtmlRight.innerHTML += right;
}

// make tabButton listener items html
function addProductHtml(left, right) {
	var boxHtmlLeft = document.getElementById('left_box');
	var boxHtmlRight = document.getElementById('right_box');
	boxHtmlLeft.innerHTML = left;
	boxHtmlRight.innerHTML = right;
}

// if category cliked, event listener
var tabmenu = document.querySelector('.event_tab_lst');
tabmenu.addEventListener('click', function(evt) {
	// css active
	var anchorList = document.querySelectorAll('.anchor');

	for (var i = 0; i < anchorList.length; i++) {
		anchorList[i].classList.remove('active');
	}
	var parent = evt.target;

	if (parent.tagName == 'SPAN') {
		parent.parentNode.classList.add('active');
	} else if (parent.className == 'anchor') {
		parent.classList.add('active');
	} else if (parent.className == 'item') {
		parent.firstElementChild.classList.add('active');
	}
	var url = 'products?';
	var start = 0;

	url = getUrl(url);
	productAjax(url, start);
});

// more buttn listener!!
var moreBtn = document.getElementById('more-btn');
moreBtn.addEventListener("click", function(evt) {
	var wrapBox = document.querySelector('.wrap_event_box');
	var url = 'products?';
	var start = wrapBox.id;

	url = getUrl(url);
	productAjax(url, start);
});

// get url each buttonListener
function getUrl(url) {
	var activeAnchor = document.querySelectorAll('.anchor');
	var categoryId = '';
	var newUrl = url;

	for (var i = 0; i < activeAnchor.length; i++) {
		if (activeAnchor[i].classList.contains('active')) {
			categoryId = i;
			break;
		}
	}

	if (categoryId == 0) {
		newUrl += 'start=';
	} else if (categoryId == 1) {
		newUrl += 'categoryId=1&start=';
	} else if (categoryId == 2) {
		newUrl += 'categoryId=2&start=';
	} else if (categoryId == 3) {
		newUrl += 'categoryId=3&start=';
	} else if (categoryId == 4) {
		newUrl += 'categoryId=4&start=';
	} else if (categoryId == 5) {
		newUrl += 'categoryId=5&start=';
	}
	return newUrl;

}