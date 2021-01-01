// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {

    var email = document.querySelector('#input_email').value;
    var getReserveInfo = "/project3/mypage?email=" + email;
    getReserveAjax(getReserveInfo);
    
    
}

//get promotion image, content, description
function getReserveAjax(url) {
	var xhr = new XMLHttpRequest();
	console.log(url)
	xhr.open('GET', url, true);
	xhr.send();
	var pmJson = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				pmJson = JSON.parse(xhr.responseText);
				console.log(pmJson);
				makeReservedInfo(pmJson);
				
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

function makeReservedInfo(reserveJson){
	
	var reserveHtml = document.querySelector('#booking_info').innerHTML;
	let cancelInnerHtml = document.querySelector('#cancel_info').innerHTML;
	
	var bindTemplate = Handlebars.compile(reserveHtml);
	let bindTemplate2 = Handlebars.compile(cancelInnerHtml);
	
	var explainHtml = document.querySelector('#confirmed_reserve');
    let cancelHtml = document.querySelector('#confirmed_cancel');
	
	let bookNewHtml = '';
	let cancelNewHtml = '';

	for(key in reserveJson.items){
		if(reserveJson.items[key].cancelFlag == 0){
			bookNewHtml += bindTemplate(reserveJson.items[key]);
		} else{
			cancelNewHtml += bindTemplate2(reserveJson.items[key]);
		}
		
	}
	cancelHtml.innerHTML = cancelNewHtml
	explainHtml.innerHTML = bookNewHtml;
	
}