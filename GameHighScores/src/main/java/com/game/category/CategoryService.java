package com.game.category;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<>(); 
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}
	
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void removeCategory(String categoryName) {
		categoryRepository.delete(categoryName);
	}
	
}
