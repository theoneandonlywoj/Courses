//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");

const app = express();
app.use(bodyParser.urlencoded({extended: true}));

app.set('view engine', 'ejs');

var items = [];

app.get("/", function(req, res){
  var dateOptions = {
    weekday: "long",
    day: "numeric",
    month: "long"
  }
  var day = new Date().toLocaleDateString("en-US", dateOptions);

  res.render('list', {'day': day, 'newListItems': items});
});

app.post("/", (req, res) => {
  item = req.body.newItem;
  items.push(item);
  res.redirect("/");
})

app.listen(3000, function(){
  console.log("Server started on port 3000.");
});
