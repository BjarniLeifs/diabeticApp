var express = require('express');

var app = express();

app.put('/register', function(req, res){
    res.contentType('json');
    res.send(JSON.stringify({status: "success"}));
});

app.listen(3000);