//jshint esversion: 6
const mongoose = require('mongoose');

let dbName = "animalsDB";
var dbConnection = mongoose.connect('mongodb://localhost:27017/' + dbName, { useNewUrlParser: true });

// Schema
const AnimalSchema = mongoose.model('animal', { name: String });

// Insert one
const kitty = new AnimalSchema({ name: 'Lincoln' });
kitty.save().then(() => {
  console.log('meow');
  mongoose.connection.close();
});

// Insert many
const kitty1 = new AnimalSchema({ name: 'Binky' });
const kitty2 = new AnimalSchema({ name: 'Hunter' });
AnimalSchema.insertMany(
  [
    kitty1,
    kitty2
  ],
  function(err){
    if (err) {
      console.log(err);
    } else {
      console.log("Done");
      mongoose.connection.close();
    }
  }
);
