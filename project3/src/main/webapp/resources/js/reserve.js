// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {
	
//    var temp = document.querySelector('#A_plus');
//    temp.addEventListener('click',function(evt){
//        this.previousSibling.previousSibling.value++;
//    });
//
//    var temp = document.querySelector('#A_minus');
//    temp.addEventListener('click',function(evt){
//        this.nextSibling.nextSibling.value--;
//    });
    var productId = document.querySelector('#productId').value;
    var imageTypeUrl = "/project3/products/reserve/" + productId;
    var priceInfoUrl = "/project3/reserve/priceInfo/" + productId;
    detailImageAjax(imageTypeUrl);
    reservePriceInfo(priceInfoUrl);
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
				console.log(pmJson);
				makeImage(pmJson);  
				
				
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

//get price, price type of product as product id
function reservePriceInfo(url){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.send();
	var pmJson = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				pmJson = JSON.parse(xhr.responseText);
				console.log(pmJson);
				bindPriceInfo(pmJson);
				
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

// bind html price information into javascript
function bindPriceInfo(json){
	var introHtml = document.querySelector('#product_count_cal').innerText;
	console.log(introHtml);
	bindTemplate = Handlebars.compile(introHtml)
	document.querySelector('.ticket_body').innerHTML = bindTemplate(json['priceInforList'][1]);	
}

function makeImage(json){
	var introHtml = document.querySelector('#reserve_image').innerText;
	console.log(introHtml);
	bindTemplate = Handlebars.compile(introHtml)
	document.querySelector('.group_visual').innerHTML = bindTemplate(json['imageTypeList']);	
}