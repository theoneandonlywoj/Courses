//jshint esversion:6
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request");

const port = 3000;
const app = express();

app.use(bodyParser.urlencoded({
  extended: true
}));


app.get("/", (req, res) => {
  res.sendFile(__dirname + "/index.html");
});

app.post("/", (req, res) => {
  var crypto = req.body.crypto;
  var fiat = req.body.fiat;

  request("https://apiv2.bitcoinaverage.com/indices/global/ticker/" + crypto + fiat, function(error, response, body){
    console.log(response.statusCode);
    // Parsing the JSON into a Javascript object
    var data = JSON.parse(body);
    // Writing a temporary response to the memory
    res.write("<h1>open week: " + data.open.week + "</h1>");
    res.write("<h1>open day: " + data.open.day + "</h1>");
    res.send();
  });
});

app.listen(port, () => {
  console.log("Listening...");
});
