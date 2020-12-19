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
    postAction.postAjax();
    btnMorePrivateInfo();
    
}

//get promotion image, content, description
function detailImageAjax(url) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.setRequestHeader("Content-type", "application/json");
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
				contorlCountProduct.calculatorTotalCount();
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

var postAction = {
		postAjax: function(){
			var submitBtn = document.querySelector('.bk_btn');
			submitBtn.addEventListener('click',function(evt){
				if(!validationCheck()){
					return false;
				};
				var xhr = new XMLHttpRequest();
				var postUrl = "/project3/reserve/insert"; 
				xhr.open('POST', postUrl, true);
				xhr.setRequestHeader("Content-type", "application/json");
			
				xhr.onreadystatechange = function() {
					if (xhr.readyState === XMLHttpRequest.DONE) {
						if (xhr.status === 200) {
						} else {
							console.log('[' + xhr.status + ']: ' + xhr.statusText);
						}
					}
				}
				var postData = {
						id:1,
						productId: parseInt(document.querySelector('#productId').value)
//						displayInfoId: parseInt(document.querySelector('#diplayInfoId').value),
//						reservationName: document.querySelector('#name').value,
//						reservationTel: document.querySelector('#tel').value,
//						reservationEmail: document.querySelector('#email').value,
//						priceList : [
//							{
//								priceId : parseInt(document.querySelector('#A_price_id').value),
//								count: parseInt(document.querySelector('#A_value').value)
//							},
//							{
//								priceId : parseInt(document.querySelector('#B_price_id').value),
//								count: parseInt(document.querySelector('#B_value').value)
//							},
//							{
//								priceId : parseInt(document.querySelector('#Y_price_id').value),
//								count: parseInt(document.querySelector('#Y_value').value)
//							}
//							
//						]
				}
				
				console.log(typeof(postData)); 
				xhr.send(JSON.stringify(postData)); // must use stringify in send statement!!!!!
			});
		}
}

//checking validation of reservation input
function validationCheck(){
	var emailValue = document.querySelector("#email").value;
	var nameValue =  document.querySelector("#name").value;
	var telValue =  document.querySelector("#tel").value;

    
    var nameValid = (/^[가-힣]{2,4}$/).test(nameValue);
    if(!nameValid)  { 
	      alert("올바르지 않은 이름입니다.");
	      return false;
    } 
    
    
    var telValid = (/^\d{3}-\d{3,4}-\d{4}$/).test(telValue);
    if(!telValid)  { 
	      alert("올바르지 않은 전화번호입니다.");
	      return false;
    } 
    
	var emailValid = (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue);
    if(!emailValid)  { 
	      alert("올바르지 않은 이메일입니다.");
	      return false;
    } 
    return true;
}

function btnMorePrivateInfo(){
	let agrBtn = document.querySelector('.btn_agreement');
	agrBtn.addEventListener('click',function(){
		let moreInfo = document.querySelector('.useragreement_details');
		if(moreInfo.classList.contains('open')){
			moreInfo.classList.remove('open');
		}else{
			moreInfo.classList.add('open')
		}
	});
	
	let agrBtn2 = document.querySelector('.btn_agreement_below');
	agrBtn2.addEventListener('click',function(){
		let moreInfo2 = document.querySelector('.useragreement_details_below');
		if(moreInfo2.classList.contains('open')){
			moreInfo2.classList.remove('open');
		}else{
			moreInfo2.classList.add('open')
		}
	});
	
	let infoChecked = document.querySelector('.chk_txt_label');
	infoChecked.addEventListener('click',function(){
		let isChecked = document.querySelector('#chk3');
		console.log(isChecked.checked);
		let sendBtn = document.querySelector('.bk_btn_wrap');
		if(!isChecked.checked){
			sendBtn.classList.remove('disable');
		}else{
			sendBtn.classList.add('disable');
		}
	})
	
	
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
		},
		calculatorTotalCount :  function(){
			var totalCount = Number(document.querySelector('#A_value').value)+
							 Number(document.querySelector('#B_value').value)+
							 Number(document.querySelector('#Y_value').value);
			console.log(totalCount);
			document.querySelector('#totalCount').innerText = totalCount;
		}

}
