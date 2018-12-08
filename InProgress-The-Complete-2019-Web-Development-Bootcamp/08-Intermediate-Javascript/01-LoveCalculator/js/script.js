function loveCalculator(){
  prompt("What is your name?");
  prompt("What is your crush name?");

  var loveScore = Math.floor(Math.random() * 100) + 1;
  alert("Your love score is: " + loveScore + "%!");
  // Custom message to the users
  if (loveScore > 70){
    alert("You love each other like crazy!");
  } else {
    alert("Ohh...");
  }
}
