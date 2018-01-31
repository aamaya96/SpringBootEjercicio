package com.game.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PLAYER")
public class Player {
	@Id
	@Column(name="user_name")
	private String userName;

	public Player() {}
	
	public Player(String userName) {
		super();
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
