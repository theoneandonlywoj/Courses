// Changing CSS property with jQuery
//$("h1").css("color", "green");

// Obtaining the property value with jQuery
var h1FontSize = $("h1").css("font-size");
console.log(h1FontSize);

/* Using  Javascript for the style is not advised.
Same result can be obtained by focusing the Javascript on the behaviour,
like f.e. adding a class or classes etc.
*/
$("h1").addClass("big-title margin-50");

// Checking if the element has given class
var hasClassMargin50 = $("h1").hasClass("margin-50");
console.log(hasClassMargin50);
