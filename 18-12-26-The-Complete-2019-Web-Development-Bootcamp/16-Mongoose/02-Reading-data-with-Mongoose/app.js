//jshint esversion: 6
const mongoose = require('mongoose');

let dbName = "animalsDB";
var dbConnection = mongoose.connect('mongodb://localhost:27017/' + dbName, { useNewUrlParser: true });

// Schema
const AnimalSchema = mongoose.model('animal', { name: String });

// Reading data
AnimalSchema.find(function(err, documents){
  if(err){
    console.log(err);
  } else {
    console.log(documents);
    documents.forEach(function(doc){
      console.log(doc);
    })
    mongoose.connection.close();
  }
});

// Select only name for given id
AnimalSchema.findById('5c1f72612dea7414e4200e7c', 'name', function(err, documents){
  if(err){
    console.log(err);
  } else {
    console.log(documents);
    mongoose.connection.close();
  }
});
