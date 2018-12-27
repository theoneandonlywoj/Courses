// Arrays in Javascript
var guestList = ['Wojciech', "Hunter", "Lincoln", "Sara"];

// First element in the Array
console.log(guestList[0]);

// Check if the element is in the Array
var guestOne = prompt("Hi! What is your name?");
var guestOneOneTheList = guestList.includes(guestOne);
console.log("Guest " + guestOne + " is on the list?: " + guestOneOneTheList);
