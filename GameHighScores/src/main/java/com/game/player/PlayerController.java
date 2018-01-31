package com.game.player;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.category.CategoryController;
import com.game.exceptions.GameError;


@RestController
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	private static final Logger logger = Logger.getLogger(CategoryController.class);
	
	@GetMapping("/players")
	public List<Player> getAllPlayers(){
		return playerService.getAllPlayers();
	}
	@PostMapping("/players")
	public ResponseEntity<?> addPlayer(@RequestBody Player player) {
		try {
			playerService.addPlayer(player);
			logger.debug(String.format("Player %s added", player.getUserName()));
			return new ResponseEntity<Player>(player, HttpStatus.CREATED);
		}catch(IdentifierGenerationException ex) {
			logger.error("Invalid player");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid player","/players", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@DeleteMapping("/players/{userName}")
	public ResponseEntity<?> removePlayer(@PathVariable String userName) {
		try {
			playerService.removePlayer(userName);
			logger.debug(String.format("Player %s removed ",userName));
			return ResponseEntity.accepted().build();
		}catch(EmptyResultDataAccessException ex) {
			logger.error(String.format("Player %s not found", userName));
			return new ResponseEntity<GameError>(new GameError(HttpStatus.NOT_FOUND,"Player not found",String.format("/players/%s", userName),ex), HttpStatus.NOT_FOUND);
		}
	}
	
}
