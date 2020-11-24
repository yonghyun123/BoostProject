// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {
	var productId = document.querySelector("#productId").value;
	var imageTypeUrl = "/project3/products/detail/" + productId;
	
	console.log(imageTypeUrl);
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
				//makeTitle(pmJson); temporal 
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