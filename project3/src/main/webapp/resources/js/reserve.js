// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {

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
				contorlCountProduct.calCulatorFuncTypeA();
				contorlCountProduct.calCulatorFuncTypeB();
				contorlCountProduct.calCulatorFuncTypeY();
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

// bind html price information into javascript
function bindPriceInfo(json){
	var introHtml = document.querySelector('#product_count_cal').innerText;
	var bindTemplate = Handlebars.compile(introHtml)
	var innerHtml = '';
	
	json['priceInforList'].forEach(function(v){
		innerHtml += bindTemplate(v);
	})
	document.querySelector('.ticket_body').innerHTML = innerHtml;	
}

// visuailization of reserve page main image
function makeImage(json){
	var introHtml = document.querySelector('#reserve_image').innerText;
	console.log(introHtml);
	bindTemplate = Handlebars.compile(introHtml)
	document.querySelector('.group_visual').innerHTML = bindTemplate(json['imageTypeList']);	
}


//using object literal javascript
var contorlCountProduct = {
		calCulatorFuncTypeA: function(){
			var plusClikcObj =  document.querySelector('#A_plus')
			plusClikcObj.addEventListener('click',function(evt){
				var totalPrice = document.querySelector('#A_total_price').innerText;
				var productPrice = document.querySelector('#A_price').innerText;
				var resultPrice = Number(totalPrice) + Number(productPrice);
				document.querySelector('#A_total_price').innerText = resultPrice;
				
				var countNum = this.previousSibling.previousSibling.value;
				countNum++;
				this.previousSibling.previousSibling.value = countNum;
				if(countNum > 0)
				{
					var minusIcon = document.querySelector('#A_minus');
					minusIcon.classList.remove('disabled');
					var productCount = document.querySelector('#A_value');
					productCount.classList.remove('disabled');
				}
			});
			
			var minusClickObj = document.querySelector('#A_minus');
			minusClickObj.addEventListener('click',function(evt){
				var countNum = this.nextSibling.nextSibling.value;
				if(countNum > 0)
				{
					var totalPrice = document.querySelector('#A_total_price').innerText;
					var productPrice = document.querySelector('#A_price').innerText;
					var resultPrice = Number(totalPrice) - Number(productPrice);
					document.querySelector('#A_total_price').innerText = resultPrice;
					
					countNum--;
					this.nextSibling.nextSibling.value = countNum;
					if(countNum == 0){
						var minusIcon = document.querySelector('#A_minus');
						minusIcon.classList.add('disabled');
						var productCount = document.querySelector('#A_value');
						productCount.classList.add('disabled');
						
					}
				}
			});
		},
		calCulatorFuncTypeB : function(){
			var plusClikcObj =  document.querySelector('#B_plus')
			plusClikcObj.addEventListener('click',function(evt){
				var totalPrice = document.querySelector('#B_total_price').innerText;
				var productPrice = document.querySelector('#B_price').innerText;
				var resultPrice = Number(totalPrice) + Number(productPrice);
				document.querySelector('#B_total_price').innerText = resultPrice;
				
				var countNum = this.previousSibling.previousSibling.value;
				countNum++;
				this.previousSibling.previousSibling.value = countNum;
				if(countNum > 0)
				{
					var minusIcon = document.querySelector('#B_minus');
					minusIcon.classList.remove('disabled');
					var productCount = document.querySelector('#B_value');
					productCount.classList.remove('disabled');
				}
			});
			
			var minusClickObj = document.querySelector('#B_minus');
			minusClickObj.addEventListener('click',function(evt){
				var countNum = this.nextSibling.nextSibling.value;
				if(countNum > 0)
				{
					var totalPrice = document.querySelector('#B_total_price').innerText;
					var productPrice = document.querySelector('#B_price').innerText;
					var resultPrice = Number(totalPrice) - Number(productPrice);
					document.querySelector('#B_total_price').innerText = resultPrice;
					
					countNum--;
					this.nextSibling.nextSibling.value = countNum;
					if(countNum == 0){
						var minusIcon = document.querySelector('#B_minus');
						minusIcon.classList.add('disabled');
						var productCount = document.querySelector('#B_value');
						productCount.classList.add('disabled');
						
					}
				}
			});
		},
		calCulatorFuncTypeY : function(){
			var plusClikcObj =  document.querySelector('#Y_plus')
			plusClikcObj.addEventListener('click',function(evt){
				var totalPrice = document.querySelector('#Y_total_price').innerText;
				var productPrice = document.querySelector('#Y_price').innerText;
				var resultPrice = Number(totalPrice) + Number(productPrice);
				document.querySelector('#Y_total_price').innerText = resultPrice;
				
				var countNum = this.previousSibling.previousSibling.value;
				countNum++;
				this.previousSibling.previousSibling.value = countNum;
				if(countNum > 0)
				{
					var minusIcon = document.querySelector('#Y_minus');
					minusIcon.classList.remove('disabled');
					var productCount = document.querySelector('#Y_value');
					productCount.classList.remove('disabled');
				}
			});
			
			var minusClickObj = document.querySelector('#Y_minus');
			minusClickObj.addEventListener('click',function(evt){
				var countNum = this.nextSibling.nextSibling.value;
				if(countNum > 0)
				{
					var totalPrice = document.querySelector('#Y_total_price').innerText;
					var productPrice = document.querySelector('#Y_price').innerText;
					var resultPrice = Number(totalPrice) - Number(productPrice);
					document.querySelector('#Y_total_price').innerText = resultPrice;
					
					countNum--;
					this.nextSibling.nextSibling.value = countNum;
					if(countNum == 0){
						var minusIcon = document.querySelector('#Y_minus');
						minusIcon.classList.add('disabled');
						var productCount = document.querySelector('#Y_value');
						productCount.classList.add('disabled');
						
					}
				}
			});
		}

}
