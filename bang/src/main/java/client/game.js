function createGame(gameName) {
	var object = {
		"type": "game",
		"data": {
			"type": "create",
			"data": gameName
		}
	}
	
	ws.send(JSON.stringify(object));
}