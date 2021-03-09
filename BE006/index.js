const express = require('express')
const expressHandlebars = require('express-handlebars')
const path = require('path')
const bodyParser = require('body-parser')
const methodOverride = require('method-override')
const redis = require('redis')

//Create Redis client
let client = redis.createClient()

client.on('connect', function () {
    console.log('Connected to Redis...')
})

//Set Port
const PORT = process.env.PORT || 4000

//Init app
const app = express()

//View Engine
app.engine('handlebars', expressHandlebars({ defaultLayout: 'main' }))
app.set('view engine', 'handlebars')

//body parser
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

//method override
app.use(methodOverride('_method'))

//Home Page
app.get('/',
    //cache.route(),
    function (req, res, next) {
        res.render('searchusers');
})

//Add user page
app.get('/user/add', //cache,
    //cache.route(),
    function (req, res, next) {
        res.render('adduser')
})

//Update user page
app.get('/user/update/:id', function(req, res, next) {
    id=req.params.id
    res.render('updateuser', {
        id: id
    })
})

//Search processing
app.post('/users/search', function (req, res, next) {
    let id = req.body.id

    //search user from cache

    client.hgetall(id, function (err, obj) {
        if (!obj) {
            res.render('searchusers', {
                error: 'User does not exist'
            })
        } else {
            obj.id = id;
            res.render('details', {
                user: obj
            })
        }
    })
})

//add user processing
app.post('/users/add', function (req, res, next) {
    let id = req.body.id
    let first_name = req.body.fname
    let last_name = req.body.lname
    let email = req.body.email
    let phone = req.body.phone

    client.setex(id, [
        'first_name', first_name,
        'last_name', last_name,
        'email', email,
        'phone', phone
    ])

    client.hmset(id, [
        'first_name', first_name,
        'last_name', last_name,
        'email', email,
        'phone', phone
    ], function (err, reply) {
        if (err) {
            console.log(err)
        } else {
            console.log(reply)
            res.redirect('/')
        }
    })
})

//delete user processing
app.delete('/users/delete/:id', function (req, res, next) {
    client.del(req.params.id)
    res.redirect('/')
})

//update user processing
app.post('/users/update/:id', function(req, res, next) {
    id = req.params.id
    first_name = req.body.fname
    last_name = req.body.lname
    email = req.body.email
    phone = req.body.phone
    client.hmset(id, [
        'first_name', first_name,
        'last_name', last_name,
        'email', email,
        'phone', phone
    ], function (err, reply) {
        if (err) {
            console.log(err)
        } else {
            console.log(reply)
            res.redirect('/')
        }
    })
})


app.listen(PORT, function () {
    console.log('Server started on port: ' + PORT)
})

// Cache middleware
function cache(req, res, next) {
    const id = req.body.id;
  
    client.get(id, (err, data) => {
      if (err) throw err;
  
      if (data !== null) {
        res.send(setResponse(username, data));
      } else {
        next();
      }
    });
  }
  