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
				makeReservedInfo(pmJson);
				
			} else {
				console.log('[' + xhr.status + ']: ' + xhr.statusText);
			}
		}
	}
}

function makeReservedInfo(reserveJson){
	var reserveHtml = document.querySelector('#booking_info').innerHTML;
	console.log(reserveHtml);
	var bindTemplate = Handlebars.compile(reserveHtml);
	var explainHtml = document.querySelector('#confirmed_reserve');
	var newHtml = '';

	for(key in reserveJson.items){
		newHtml += bindTemplate(reserveJson.items[key]);
	}

	explainHtml.innerHTML = newHtml;
	
}