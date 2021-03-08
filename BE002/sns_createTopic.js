// Load the AWS SDK for Node.js
var AWS = require('aws-sdk');

//inputting form user
const prompt = require('prompt-sync')();
 
const region = prompt('Which region do you want to create a topic in');
const topicName = prompt('What is the name of the topic you want to create');

// Set region
//SET THIS
AWS.config.update({region: region});

// Create promise and SNS service object
//SET TOPIC NAME
var createTopicPromise = new AWS.SNS({apiVersion: '2010-03-31'}).createTopic({Name: topicName}).promise();

// Handle promise's fulfilled/rejected states
createTopicPromise.then(
  function(data) {
    console.log("Topic ARN is " + data.TopicArn);
  }).catch(
    function(err) {
    console.error(err, err.stack);
  });
