//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

app.set('view engine', 'ejs');

let items = [];
let workItems = [];

app.get("/", function(req, res){
  var dateOptions = {
    weekday: "long",
    day: "numeric",
    month: "long"
  }
  let day = new Date().toLocaleDateString("en-US", dateOptions);

  res.render('list', {listTitle: day, newListItems: items, listType:"Personal"});
});

app.post("/", (req, res) => {
  let item = req.body.newItem;
  if (req.body.submitButton === "Work"){
    workItems.push(item);
    res.redirect("/work");
  } else {
    items.push(item);
    res.redirect("/");
  }
});

app.get("/work", (req, res) => {
  res.render("list", {listTitle: "Work List", newListItems: workItems, listType:"Work"});
});

app.listen(3000, function(){
  console.log("Server started on port 3000.");
});
