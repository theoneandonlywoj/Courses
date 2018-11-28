/*

For this quiz, can you use this script, which is linked in the <head> of index.html,
to change the boring placeholder image to a picture of a cute animal?

Remember, you'll need to pass a function into the jQuery object to run
when the document is ready.

Unfortunately, placepuppy is no longer available. Here's a link to a random
animal image on lorempixel.com:
http://lorempixel.com/350/150/animals/

Good luck!

*/
// Use "$(someFunction);" to load it when the page is ready.
$(function() {
    $('img').attr('src', 'http://haytorkennels.co.uk/wp-content/uploads/2014/03/small-animals.jpg');
});