//jshint esversion:6
const express = require("express");
const app = express();
const port = 3000;
// Adding body parses
const bodyParser = require("body-parser");
// urlencoded is a pody-parser mode to parse the data coming from the body
// Extended = true allows to post nested objects
app.use(bodyParser.urlencoded({extended: true}));
// Simple end-point test
app.get("/", (req, res) => {
  // __dirname gets the absolute path for the directory where the file is in.
  res.sendFile(__dirname + "/index.html");
});

app.post("/calculate", function(req, res){
  // Getting variables from the body
  var requestBody = req.body;
  var num1 = Number(requestBody.num1);
  var num2 = Number(requestBody.num2);
  res.send("The result of the calculations is: " + (num1 + num2));
});

app.listen(port, () => {
  console.log("Listening...");
});
