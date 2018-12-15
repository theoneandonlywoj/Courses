//jshint esversion:6
const express = require("express");
const app = express();
const port = 3000;
// Simple end-point test
app.get("/", (req, res) => {
  // __dirname gets the absolute path for the directory where the file is in.
  res.sendFile(__dirname + "/index.html");
});

app.listen(port, () => {
  console.log("Listening...");
});
