//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");

const app = express();

app.set('view engine', 'ejs');

app.get("/", function(req, res){
  var todayDayOfTheWeekNumber = new Date();
  var day = "";

  if (todayDayOfTheWeekNumber === 6 | todayDayOfTheWeekNumber === 0){
    day = "weekend";
  } else {
    day = "weekday";
  }
  res.render('list', {'day': day});
});

app.listen(3000, function(){
  console.log("Server started on port 3000.");
});
