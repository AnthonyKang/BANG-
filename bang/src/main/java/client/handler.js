function handle(json) {
	var type = json["type"];

	if (type === "connected") {
		selfname = json["data"];
		$("p#self-name").text("Username = " + selfname);
	}
	else if (type === "userlist") {
		console.log("-updating user list");
		updateUserList(json["data"])
	}
	else if (type === "gamelist") {
		console.log("-updating game list");
		updateGameList(json["data"]);
	}
	else if (type === "joingame") {
		var data = json["data"]

		if (data["status"]) {
      		selfgamename = data["gameName"];
      		$("p#current-game").text("Game = " + data["gameName"]);
      		var button = $("button#game-" + selfgamename);
      		button.text("Leave");
      		button.toggleClass("join-game-button", false);
      		button.toggleClass("leave-game-button", true);
		}
	}
	else if (type === "leavegame") {		
		var data = json["data"]

		if (data["status"]) {
      		selfgamename = null;
      		$("p#current-game").text("Game = ");
      		var button = $("button#game-" + selfgamename);
      		button.text("Join");
      		button.toggleClass("join-game-button", true);
      		button.toggleClass("leave-game-button", false);
		}
	}
}