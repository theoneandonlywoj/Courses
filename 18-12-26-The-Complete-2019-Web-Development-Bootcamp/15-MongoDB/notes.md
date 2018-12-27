# MongoDB
## Starting a local server
```bash
mongod
```
##

## Connecting to the local server
```bash
mongo
```

## Show Databases
```bash
show dbs
```

## Switch / create a new database
```bash
use databaseName
```

## Show currently used database
```bash
db
```

## Insert a document into a collection (inserOne, insertMany)
```bash
db.collectionName.insertOne(
  {   
      _id: 1,
      firstName: "Wojciech",
      secondName: "Orzechowski"
  }
)
```
## Read all the data from the collection
```bash
db.collectionName.find()
```

## Read a specific data
```bash
db.collectionName.find({_id: 1})
```

## Read a specific data with value greater or equal to a parameter
```bash
db.collectionName.find({_id: {$gte: 1}})
```

## Query operators
https://docs.mongodb.com/manual/reference/operator/query-comparison/#query-selectors-comparison

## Projection (column names to return, 1 = True, 0 = False)
```bash
db.collectionName.find({_id: {$gte: 1}}, {firstName: 1, _id: 0})
```
## Update record(s) (updateOne, updateMany)
```bash
db.collectionName.updateOne({_id: 1}, {$set: {nickname: "arcyfelix"}})
```

## Delete record(s) (deleteOne, deleteMany)
```bash
db.collectionName.deleteOne({_id: 1})
```
