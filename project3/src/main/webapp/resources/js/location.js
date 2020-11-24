/**
 * 객체리터럴을 이용한 tabUI 구현
 * 상세보기에서 상세정보와 오시는 길 tab 기능을 객체리터럴을 이용해 구현
 */
var DetailTab = {
	productId : document.querySelector("#productId"),
	registerEvents : function() {
		var tabMenu = document.querySelector('.info_tab_lst');
		tabMenu.addEventListener('click', function(evt) {
			var anchorList = document.querySelectorAll('.anchor');
			var innerDetail = document.querySelector('.detail_area_wrap');
			var innerPath = document.querySelector('.detail_location');

			if (evt.target.innerText === '상세정보') {
				anchorList.forEach(function(v) {
					if (v.id !== 'detail') {
						v.classList.remove('active');
					} else {
						v.classList.add('active');
					}
				});

				if (innerDetail.classList.contains('hide')) {
					innerDetail.classList.remove('hide');
					innerPath.classList.add('hide');
				}

			} else if (evt.target.innerText === '오시는길') {
				anchorList.forEach(function(v) {
					if (v.id !== 'path') {
						v.classList.remove('active');
					} else {
						v.classList.add('active');
					}
				});

				if (innerPath.classList.contains('hide')) {
					innerPath.classList.remove('hide');
					innerDetail.classList.add('hide');
				}
				var locationInfoUrl = '/project3/products/detail/locationInfo/'
						+ displayId.value;
				this.sendAjax(locationInfoUrl);
			}
		}.bind(this));
	},

	sendAjax : function(url) {
		var xhr = new XMLHttpRequest();
		xhr.addEventListener("load", function() {
			var data = JSON.parse(xhr.responseText);
			this.makePathLocation(data);
		}.bind(this));
		xhr.open("GET", url);
		xhr.send();
	},

	makePathLocation : function(detailJson) {
		var originHtml = document.querySelector('#path-item').innerText;
		var bindTemplate = Handlebars.compile(originHtml);
		var parentHtml = document.querySelector('.detail_location');
		parentHtml.innerHTML = bindTemplate(detailJson['locationInfo'][0]);
	}
};


