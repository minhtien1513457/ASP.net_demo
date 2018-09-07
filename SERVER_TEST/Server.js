var express = require("express");
var app = express();
var http = require('http').Server(app);
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
server.listen(process.env.PORT || 3000);
console.log("Server running");

app.get('/', function (req, res) {
  res.send('<h1>Welcome to my server</h1>');
});

io.sockets.on('connection', function(socket) {
  console.log("Co nguoi ket noi: "+socket.id);
  
  socket.on("disconnect",function(){
	  console.log(socket.id+" ngat ket noi!!!");
  });
  
  socket.on('client-send-data',function(data){
	  console.log("server nhan: "+data);
	  io.sockets.emit('server-send-data',{ noidung : data});
  });
});