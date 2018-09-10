using first.Models;
using Quobject.SocketIoClientDotNet.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Runtime;

namespace first.Controllers
{
	public class MoviesController : Controller
	{
		//
		// GET: /Movie/Random
		public ActionResult Random()
		{
			var movie = new Movie() { Name = "Harry" };
			var socket = IO.Socket("http://localhost:3000");
			socket.On(Quobject.SocketIoClientDotNet.Client.Socket.EVENT_CONNECT, () =>
			{
				socket.Emit("client-send-data", "trungbede");
				socket.On("server-send-data", (data) =>
				{
					//movie.Name = "aaa";
					movie.Name = data.ToString();
					//socket.Disconnect();
				});				
			});
			return View(movie);
		}
	}
}