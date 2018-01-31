package com.game.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	
	public List<Player> getAllPlayers(){
		List<Player> players = new ArrayList<>();
		playerRepository.findAll().forEach(players::add);
		return players;
	}
	
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	public Player getPlayer(String userName) {
		return playerRepository.findOne(userName);
	}
	
	public void removePlayer(String userName) {
		playerRepository.delete(userName);	
	}
	
}
