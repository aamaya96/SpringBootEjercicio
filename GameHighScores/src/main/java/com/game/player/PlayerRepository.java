package com.game.player;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,String>{
	
	//public List<Player> findTop10AllByOrderByUserScoreDesc();
	//public List<Player> findTop10AllByCategoryCategoryNameOrderByUserScoreDesc(String categoryName);

}
