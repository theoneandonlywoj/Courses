//jshint esversion: 6
const mongoose = require('mongoose');

let dbName = "animalsDB";
var dbConnection = mongoose.connect('mongodb://localhost:27017/' + dbName, { useNewUrlParser: true });

// Schema
const AnimalSchema = mongoose.model('animal', { name: String });

// Update data (updateOne or updateMany)
AnimalSchema.updateOne({_id: "5c1f72612dea7414e4200e7b"}, {name: "Linci"}, function(err){
  if (err) {
    console.log(err);
  } else {
    console.log("Update Status: Done");
  }
  mongoose.connection.close();
});

// Delete data (deleteOne or deleteMany)
AnimalSchema.deleteOne({_id: "5c1f72612dea7414e4200e7b"}, function(err){
  if (err) {
    console.log(err);
  } else {
    console.log("Deleting Status: Done");
  }
  mongoose.connection.close();
});
