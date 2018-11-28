/*
For this quiz, remove the class 'featured' from Article #2 and add it to Article #3!

You must use jQuery's toggleClass method!
*/
function nextAndToggleClass(){
	var article2, article3;
	// your code goes here!
	article2 = $('.featured')// your code starts here!
	article3 = article2.next()
	article2.toggleClass("featured")
	console.log("Article 2 is not 'featured' anymore!")
	article3.toggleClass("featured")
	console.log("Article 3 is 'featured' now!")
}