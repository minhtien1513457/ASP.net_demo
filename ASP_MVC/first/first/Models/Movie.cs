using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Net.Sockets;
using Quobject.SocketIoClientDotNet.Client;
namespace first.Models
{
    public class Movie
    {
        public int Id { set; get; } 
        public string Name { set; get; }
        public void connectServer()
        {
             var socket = IO.Socket("http://localhost:3000");
            socket.On(Quobject.SocketIoClientDotNet.Client.Socket.EVENT_CONNECT, () =>
            {
                socket.Emit("hi");

            });

            socket.On("hi", (data) =>
            {
                Console.WriteLine(data);
                socket.Disconnect();
            });
            Console.ReadLine();
        }
    }
}