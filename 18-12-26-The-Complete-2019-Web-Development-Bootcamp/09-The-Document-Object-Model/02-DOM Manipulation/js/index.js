/* Accessing properties examples:
.innerHTML
.style
.firstChild

Accessing methods examples:
.click()
.appendChild()
.setAttribute
*/

console.log(document.getElementsByTagName("li"));
// Changing the content of the third bullet point to Wojciech
// Selecting by the tag name
document.getElementsByTagName("li")[2].innerText = "Wojciech";

// Selecting by the class name
document.getElementsByClassName("btn")[0].style.color = "red";

// Selecting the ID
// Warning! It does return only one element!
document.getElementById("second").innerHTML = "Hi!";

/* Query selector
Works similarly to class css
  tag
  .class
  #id
*/
// That returns only the first item
console.log(document.querySelector("li.item"));

// That returns all objects that match the querySelector as an array
console.log(document.querySelectorAll("li.item"));

// Changing the color of the Google link text with a button press
var googleLink = document.querySelector("li a");
var button = document.getElementsByClassName("btn")[0];

button.addEventListener("click", function(e){
    console.log("Button clicked!");
    // The properties can be found:
    // https://www.w3schools.com/jsref/dom_obj_all.asp
    googleLink.style.color = "red";
    button.style.visibility = "hidden";

    /* Adding and deleting classes can be done by adding or removing a class from the class list:
    f.e:
    1) adding: document.querySelector("h1").classList.add("newClass");
    2) removing: document.querySelector("h1").classList.remove("newClass");
    3) toggle (add not added, remove if already added):
       document.querySelector("h1").classList.toggle("newClass");
    */

    /*
    Attributes:
    1) Obtaining list of attibutes (like href or src): document.querySelector("a").attributes;
    2) Obtaining the attribute: document.querySelector("a").getAttribute("href");
    3) Set a new value to an attribute: document.querySelector("a").setAttribute("href", "https://www.bing.com");
    */
}, false);
