/*
When you click on an article, its background will change to red.
*/

$(document).ready(function() {
    $('.article-item').on('click', function( evt ) {
        $( evt.target ).css( 'background', 'red' );
        console.log(evt);
    });
});