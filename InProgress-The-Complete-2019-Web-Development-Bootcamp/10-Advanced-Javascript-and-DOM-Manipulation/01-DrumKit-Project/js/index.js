// Adding an Event Listener
// The name of the function (without the "()") is passed to the event listener
// Adding the event listener to all button (with class drum)
for (var i = 0; i < document.querySelectorAll(".drum").length; i++){
  document.querySelectorAll(".drum")[i].addEventListener("click", function(){
    alert("I just got clicked!");
  });
}
