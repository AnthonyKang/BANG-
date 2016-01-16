function createGame(userName, gameName) {
	var object = {
		"type": "game",
		"data": {
			"action": "create",
			"userName": userName,
			"gameName": gameName
		}
	};

	ws.send(JSON.stringify(object));
}

function joinGame(userName, gameName) {
	var object = {
		"type": "game",
		"data": {
			"action": "join",
			"userName": userName,
			"gameName": gameName
		}
	};

	ws.send(JSON.stringify(object));
}

function leaveGame(userName, gameName) {	
	var object = {
		"type": "game",
		"data": {
			"action": "leave",
			"userName": userName,
			"gameName": gameName
		}
	};

	ws.send(JSON.stringify(object));
}