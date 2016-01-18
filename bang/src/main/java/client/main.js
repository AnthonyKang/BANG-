var selfname = null;
var selfgamename = null;

$(document).ready(function() {
   $("button#ws-connect").click(function() {
      connect();
   });

   $("button#ws-disconnect").click(function() {
      disconnect();
   });

   $("button#game-create").click(function() {
      console.log("+creating game");
      var inputBox = $("input#game-name")
      var gamename = inputBox.val()
      inputBox.val('')
      createGame(selfname, gamename);
   });

   $("ol#game-list").on("click", ".join-game-button", function() {
      if (selfgamename != null) {
         return;
      }
      var gamename = $(this).attr('id').replace("game-", "");
      console.log("+joining game: " + gamename);
      joinGame(selfname, gamename);
   });

   $("ol#game-list").on("click", ".leave-game-button", function() {
      var gamename = $(this).attr('id').replace("game-", "");
      console.log("+leaving game: " + gamename);
      leaveGame(selfname, gamename);
   });

});

function getUserList() {
   var message = {
      "type": "user",
      "data": {
         "action": "getList"
      
}   };
   ws.send(JSON.stringify(message));
};

function getGameList() {
   var message = {
      "type": "game",
      "data": {
         "action": "getList"
      }
   };
   ws.send(JSON.stringify(message));
};

function updateUserList(userList) {
   var list = $("ol#user-list");
   list.empty();

   if (Object.keys(userList).length == 0) {
      list.append("<p class='empty-list'><i>No entries</i></p>");
      return;   
   }


   $.each(userList.sort(), function(index, username) {
      if (username === selfname) {
         list.append("<li><b>" + username + "</b></li>")
      }
      else {
         list.append("<li>" + username + "</li>")
      }
   });
}

function updateGameList(gameList) {
   var list = $("ol#game-list");
   list.empty();
   
   if (Object.keys(gameList).length == 0) {
      list.append("<p class='empty-list'><i>No entries</i></p>");
      return;   
   }
   
   var gameNames = Object.keys(gameList).sort();

   $.each(gameNames.sort(), function(index, gameName) {
      // list.append("<li>" + gameName + "<button class='join-game-button'>Join</button></li>");
      var item = "<li>" + gameName + " - ";
      var players = gameList[gameName];
      
      if (players.length == 0) {
         item = item + "<i>none </i>";
      }
      else {
         $.each(players.sort(), function(index, playerName) {
            if (playerName === selfname) {
               item = item + "<b>" + playerName + "</b> ";
            }
            else {
               item = item + playerName + " ";
            }
         });
      }

      if (gameName === selfgamename) {
         item = item + "<button id='game-" + gameName + "' class='leave-game-button'>Leave</button></li>";
      }
      else {
         item = item + "<button id='game-" + gameName + "' class='join-game-button'>Join</button></li>";
      }
      list.append(item);
   });
}