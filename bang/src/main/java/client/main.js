$(document).ready(function() {
   $("button#ws-connect").click(function() {
      console.log("connect clicked");
      connect();
   });

   $("button#ws-disconnect").click(function() {
      console.log("disconnect clicked");
      disconnect();
   });

   $("button#game-create").click(function() {
      console.log("creating game");
      var inputBox = $("input#game-name")
      var gamename = inputBox.val()
      inputBox.val('')
      createGame(gamename);
   })
});

var selfname = "";

function joinGame(element) {

}

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
   
   console.log(gameList)
   var gameNames = Object.keys(gameList).sort();

   $.each(gameNames.sort(), function(index, gameName) {
      console.log("Parsing game: " + gameName)
      // list.append("<li>" + gameName + "<button class='join-game-button'>Join</button></li>");
      var item = "<li>" + gameName + " - ";
      var players = gameList[gameName]
      if (players.length == 0) {
         item = item + "<i>none </i>"
      }
      else {
         $.each(players.sort()), function(index, playerName) {
            item = item + playerName + " "
         }
      }
      item = item + "<button class='join-game-button'>Join</button></li>"
      list.append(item);
   });
}