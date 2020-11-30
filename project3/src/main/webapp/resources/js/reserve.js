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
}