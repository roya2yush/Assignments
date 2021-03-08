// Load the AWS SDK for Node.js
var AWS = require('aws-sdk');

// Set region
AWS.config.update({region: region});

// Create subscribe/email parameters
var params = {
  Protocol: http, /* required */
  TopicArn: topicArn, /* required */
  Endpoint: 'http://localhost/index.js/' //configure this
};

// Create promise and SNS service object
var subscribePromise = new AWS.SNS({apiVersion: '2010-03-31'}).subscribe(params).promise();

// Handle promise's fulfilled/rejected states
subscribePromise.then(
  function(data) {
    console.log("Subscription ARN is " + data.SubscriptionArn);
  }).catch(
    function(err) {
    console.error(err, err.stack);
  });