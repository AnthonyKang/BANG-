function handle(json) {
	var msgType = json.msgType;

	if (json.msgType === "connected") {
		selfname = json.username;
		$("p#self-name").text("Username = " + selfname);
	}
	else if (json.msgType === "userlist") {
		console.log("updating user list");
		updateUserList(json.userlist)
	}
	else if (json.msgType === "gamelist") {
		console.log("updating game list");
		updateGameList(json.gamelist);
	}
}