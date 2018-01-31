package com.game.category;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id
	@Column(name="category_name")
	private String categoryName;

	public Category() {}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;	
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
