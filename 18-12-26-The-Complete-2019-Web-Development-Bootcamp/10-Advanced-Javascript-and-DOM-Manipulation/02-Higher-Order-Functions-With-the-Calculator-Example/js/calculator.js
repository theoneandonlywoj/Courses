function add(a, b){
  return a + b;
}

function multiplication(a, b){
  return a * b;
}

function calculator(a, b, operator){
  return operator(a, b);
}

console.log("5 + 3 with calculator and add functions:");
console.log(calculator(5, 3, add));

console.log("5 * 3 with calculator and multiplication functions:");
console.log(calculator(5, 3, multiplication));
