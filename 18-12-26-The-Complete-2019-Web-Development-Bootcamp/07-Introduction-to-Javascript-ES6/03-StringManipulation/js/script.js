// Twitter-like message length counter
var tweet = prompt("Your Tweet:");
var tweetLength = tweet.length;
window.alert(tweet);
window.alert("You have written " + tweetLength + " character(s), you have left " + (180 - tweetLength) + " character(s).");

// SLicing a string
var name = "Wojciech";
var firstChar = name.slice(0, 1);
console.log(firstChar);

// Chainging the case of a string
var nameUppderCased = name.toUpperCase();
var nameLowerCased = name.toLocaleLowerCase();
console.log(nameUppderCased);
console.log(nameLowerCased);
