//jshint esversion:6
const express = require("express");
const bodyParser = require("body-parser");

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
const port = 3000;

// Helper function
function bmi(height, weight){
  return weight / (height * height);
}

// Endpoints
app.get("/", (req, res) => {
  res.sendFile(__dirname + "/html/index.html");
});

app.post("/bmicalculator", (req, res) => {
  var requestBody = req.body;
  var weight = parseFloat(requestBody.weight);
  var height = parseFloat(requestBody.height / 100);
  res.send("Your BMI is: " + Math.floor(bmi(height, weight)));
});

app.listen(port, (req, res) => {
  console.log("Listening...");
});
