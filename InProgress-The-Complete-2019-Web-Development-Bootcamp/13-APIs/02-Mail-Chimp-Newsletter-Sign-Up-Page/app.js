// jshint esversion:6
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request");

const app = express();
const port = 3000;

// Adding reference to the public folder where the CSS files and the images are stored
app.use(express.static("public"));
// Using body parser
app.use(bodyParser.urlencoded({
  extended: true
}));

app.get("/", function(req, res) {
  res.sendFile(__dirname + "/signup.html");
});

app.post("/", function(req, res) {
  var firstName = req.body.fName;
  var lastName = req.body.lName;
  var email = req.body.email;

  var data = {
    members: [{
      email_address: email,
      status: "subscribed",
      merge_fields: {
        "FNAME": firstName,
        "LNAME": lastName
      }
    }]
  };
  var jsonData = JSON.stringify(data);
  console.log(firstName, lastName, email);

  /* Mailchimp APIs
  https://developer.mailchimp.com/documentation/mailchimp/reference/lists/#create-post_lists_list_id
  */
  const listID = "07f1540996";
  const authKey = "de352bf036830289b7d80e3a712f2c7c-us7";
  var options = {
    url: "https://us7.api.mailchimp.com/3.0/lists/" + listID,
    method: "POST",
    headers: {
      "Authorization": "W1 " + authKey
    },
    body: jsonData
  };

  request(options, (error, response, body) => {
    if (error) {
      res.sendFile(__dirname + "/failure.html");
    } else {
      if (response.statusCode === 200) {
        res.sendFile(__dirname + "/success.html");
      } else {
        res.send("Something was not correct.");
      }
    }
  });
});

app.post("/failure", function(req, res) {
	res.redirect("/");
});

app.listen(port, () => {
  console.log("Listening...");
});
