/*
    Adding event delegation for class article-list and its children.
*/

$(document).ready(function() {
    $('.article-list').on('click', function(){
	   console.log('Clicked');
    }); 
});