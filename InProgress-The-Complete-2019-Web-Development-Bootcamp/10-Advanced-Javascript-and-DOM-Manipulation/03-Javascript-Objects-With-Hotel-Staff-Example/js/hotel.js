/* Creating a Javascript object:
  The name of the function MUST HAVE the first letter capitalized
*/

function HouseKeeper(yearsOfExperience, name, cleaningRepertoire){
  this.yearsOfExperience = yearsOfExperience;
  this.name = name;
  this.cleaningRepertoire = cleaningRepertoire;
  // Adding a function to the constructor
  this.clean = function() {
    alert("Cleaning in progress...");
  };
}

// Creating a new HouseKeeper objects
var houseKeeper1 = new HouseKeeper(9, "Tom", ["lobby", "bedrooms"]);
var houseKeeper2 = new HouseKeeper(5, "Jerry", ["lobby"]);
console.log(houseKeeper1.name);
houseKeeper1.clean();
