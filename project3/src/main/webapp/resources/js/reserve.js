// 첫 화면 ajax
// DOM init function
document.addEventListener('DOMContentLoaded', function() {
	init();
})

function init() {
	
    var temp = document.querySelector('#A_plus');
    temp.addEventListener('click',function(evt){
        this.previousSibling.previousSibling.value++;
    });

    var temp = document.querySelector('#A_minus');
    temp.addEventListener('click',function(evt){
        this.nextSibling.nextSibling.value--;
    });
    var productId = document.querySelector('#productId').value;
    var imageTypeUrl = "/project3/products/reserve/" + productId;
    detailImageAjax(imageTypeUrl)
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

function makeImage(json){
	var introHtml = document.querySelector('#reserve_image').innerText;
	console.log(introHtml);
	bindTemplate = Handlebars.compile(introHtml);

	document.querySelector('.group_visual').innerHTML = bindTemplate(json['imageTypeList']);	
}