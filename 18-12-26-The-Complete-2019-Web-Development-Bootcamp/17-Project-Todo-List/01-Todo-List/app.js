

const express = require("express");
const bodyParser = require("body-parser");
// Importing an external module
const date = require(__dirname + "/date.js");
const mongoose = require("mongoose");

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

app.set('view engine', 'ejs');

// Mongodb connection
mongoose.connect("mongodb://localhost:27017/todoListDB", {useNewUrlParser: true});

// Item Schema
const itemsSchema = {
  name: String
};

const Item = mongoose.model("personalitems", itemsSchema);
const WorkItem = mongoose.model("workitems", itemsSchema);
/*
const item1 = new Item({
  name: "Item1"
});
Item.insertMany([item1], function(err){
  console.log("Done");
});
*/
app.get("/", function(req, res) {
  Item.find({}, function(err, items){
    res.render('list', {listTitle: "Today", newListItems: items, listType: "Personal"});
  });
});

app.post("/", (req, res) => {
  let itemName = req.body.newItem;
  if (req.body.submitButton === "Work"){
    let myItem = new WorkItem({name: itemName});
    myItem.save().then(()=>{
      res.redirect("/work");
    });
  } else {
    let myItem = new Item({name: itemName});
    myItem.save().then(()=>{
      res.redirect("/");
    });
  }
});

app.get("/work", (req, res) => {
  WorkItem.find({}, function(err, items){
    res.render('list', {listTitle: "Work List", newListItems: items, listType: "Work"});
  });
});

app.listen(3000, function(){
  console.log("Server started on port 3000.");
});
