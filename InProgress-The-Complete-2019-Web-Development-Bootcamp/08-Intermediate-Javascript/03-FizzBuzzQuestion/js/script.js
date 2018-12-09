alert("FizzBuzz");
var output = [];
var count = 1;

function fizzBuzz() {
  if (count % 3 === 0 && count % 5 === 0) {
    output.push("FizzBuzz");
  } else if (count % 3 === 0) {
    output.push("Fizz");
  } else if (count % 5 === 0) {
    output.push("Buzz");
  } else {
    output.push(count);
  }
  count++;
}


function fizzBuzzGame() {
  for (i = 0; i <= 100; i++) {
    fizzBuzz();  
  }
  console.log(output);
}

fizzBuzzGame();
