// Robot example
function goToShop(){
  console.log("leaveHouse");
  console.log("moveRight");
  console.log("moveRight");
  console.log("moveUp");
  console.log("moveUp");
  console.log("moveUp");
  console.log("moveUp");
  console.log("moveRight");
  console.log("moveRight");
}

function comeBackHome(){
  console.log("moveLeft");
  console.log("moveLeft");
  console.log("moveDown");
  console.log("moveDown");
  console.log("moveDown");
  console.log("moveDown");
  console.log("moveLeft");
  console.log("moveLeft");
  console.log("enterHouse");
}

function getMilkNumberBottles(bottles) {
  var cost = bottles * 1.5;

  goToShop();
  console.log("buy " + bottles + " bottle(s) of milk");
  comeBackHome();
}

function getMilkWithMoney(money) {
  var pricePerBottle = 1.5;
  var bottlesBought = Math.floor(money  / pricePerBottle);

  goToShop();
  console.log("Bought " + bottlesBought + " bottle(s) of milk");
  comeBackHome();
  return money % pricePerBottle;
}
