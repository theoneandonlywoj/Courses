// Adding an Event Listener
// The name of the function (without the "()") is passed to the event listener
document.querySelector("button").addEventListener("click", handleClick);

function handleClick(){
  alert("I got clicked");
}
