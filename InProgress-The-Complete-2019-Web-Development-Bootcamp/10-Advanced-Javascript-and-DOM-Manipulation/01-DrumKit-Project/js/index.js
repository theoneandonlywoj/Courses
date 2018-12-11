// Adding an Event Listener
// The name of the function (without the "()") is passed to the event listener
// Adding the event listener to all buttons (with class drum, for the mouse) 
for (var i = 0; i < document.querySelectorAll(".drum").length; i++) {
  document.querySelectorAll(".drum")[i].addEventListener("click", function() {
    // Switching based on the letter inside the inner HTML of each button
    var buttonInnerHTML = this.innerHTML;
    playSound(buttonInnerHTML);
  });
}

// Adding a keypress event Listener to the whole page
document.addEventListener("keypress", function(event){
  var key = event.key;
  console.log(key);
  playSound(key);
});

// Playing the relevant sound
function playSound(key){
  switch (key) {
    case "w":
      var tom1 = new Audio("sounds/tom-1.mp3");
      tom1.play();
      break;

    case "a":
      var tom2 = new Audio("sounds/tom-2.mp3");
      tom2.play();
      break;

    case "s":
      var tom3 = new Audio("sounds/tom-3.mp3");
      tom3.play();
      break;

    case "d":
      var tom4 = new Audio("sounds/tom-4.mp3");
      tom4.play();
      break;

    case "j":
      var snare = new Audio("sounds/snare.mp3");
      snare.play();
      break;

    case "k":
      var crash = new Audio("sounds/crash.mp3");
      crash.play();
      break;

    case "l":
      var kick = new Audio("sounds/kick-bass.mp3");
      kick.play();
      break;
    default:
      console.log(key);
    }
}
