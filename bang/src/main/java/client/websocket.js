var ws = null;

// connect to ws
function connect() {
   console.log("connecting");
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
      console.log("message type: " + obj.msgType);
      handle(obj);
   };
      
   ws.onclose = function() {
      $("p#connection-status").text("Status = Disconnected");
      $("p#self-name").text("Username = ");
      $("div#info").attr("style", "visibility: hidden")
      console.log("ws closed")
   };
}

// disconnect from ws
function disconnect() {
   if (!ws) {
      return;
   }
   ws.close();
   $("ol#user-list").empty();
   ws = null;
}

// return true if open; false if closed
function getWsStatus() {
   return ws != null
}