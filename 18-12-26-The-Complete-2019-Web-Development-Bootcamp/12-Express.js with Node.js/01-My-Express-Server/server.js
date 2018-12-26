//jshint esversion:6

// Require Express
const express = require("express");
// Intialize the app
const app = express();
const port = 3000;

// Defining a simple GET Hello, World endpoint
app.get('/', (req, res) => {
  res.send('Hello, World!');
});
// Listen on port 3000 and returning a simple information
app.listen(port, () => {
  console.log("Listening...");
});
