// jshint esversion:6
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request");

const app = express();
const port = 3000;

// Adding reference to the public folder where the CSS files and the images are stored
app.use(express.static("public"));

app.get("/", function(req, res){
  res.sendFile(__dirname + "/signup.html");
});

app.listen(port, ()=> {
  console.log("Listening...");
});
