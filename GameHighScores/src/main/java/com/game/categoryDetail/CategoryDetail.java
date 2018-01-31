package com.game.categoryDetail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.game.category.Category;
import com.game.player.Player;

@Entity
public class CategoryDetail {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryDetailId;
	public int getCategoryDetailId() {
		return categoryDetailId;
	}

	public void setCategoryDetailId(int categoryDetailId) {
		this.categoryDetailId = categoryDetailId;
	}

	@ManyToOne
	@JoinColumn(name="user_name")
	private Player player;
	@ManyToOne
	@JoinColumn(name="category_name")
	private Category category;
	@Column(name="level")
	private int level;
	@Column(name="score")
	private long score;
	
	public CategoryDetail() {}
	
	public CategoryDetail(Category category,Player player,int level, long score) {
		super();
		this.category = category;
		this.player = player;
		this.level = level;
		this.score = score;
	}
	
	
	public CategoryDetail(int categoryDetailId, Category category, Player player, int level, long score) {
		super();
		this.categoryDetailId = categoryDetailId;
		this.category = category;
		this.player = player;
		this.level = level;
		this.score = score;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Player: " + this.player.getUserName() + " Player: " + this.category.getCategoryName() 
				+ " Level: " +  this.level + " Score:" + this.score;
	}
}
