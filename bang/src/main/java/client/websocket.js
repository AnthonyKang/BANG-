var ws = null;

// connect to ws
function connect() {
   console.log("+connecting");
   if (getWsStatus()) {
      console.log("already open");
      return;
   }
   // open a web socket
   ws = new WebSocket("ws://localhost:8080/testsocket");
   console.log("ws opened");
   
   ws.onopen = function() {
      $("p#connection-status").text("Status = Connected");
      $("div#info").attr("style", "visibility: visible")
   }

   ws.onmessage = function (evt) {
      var msg = evt.data;
      console.log("received: " + msg);
      var obj = JSON.parse(msg);
      handle(obj);
   };
      
   ws.onclose = function() {

      $("p#connection-status").text("Status = Disconnected");
      $("p#self-name").text("Username = ");
      $("div#info").attr("style", "visibility: hidden")
      ws = null
      console.log("ws closed")
   };
}

// disconnect from ws
function disconnect() {
   ws.close();
   selfname = null;
   selfgamename = null;
}

// return true if open; false if closed
function getWsStatus() {
   // TODO: useless if connection fails
   return ws != null
}