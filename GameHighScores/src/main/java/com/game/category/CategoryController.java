package com.game.category;

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

import com.game.exceptions.GameError;


@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = Logger.getLogger(CategoryController.class);
	
	@GetMapping("/categories")
	public List<Category> getCategories(){
		return categoryService.getAllCategories(); 
	}
	
	@PostMapping("/categories")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		try {
			categoryService.addCategory(category);
			logger.debug(String.format("Cateory %s added",category.getCategoryName()));
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		}catch(IdentifierGenerationException ex) {
			logger.error("Invalid category");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid category","/categories", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/categories/{categoryName}")
	public ResponseEntity<?> removeCategory(@PathVariable String categoryName) {
		try {
			categoryService.removeCategory(categoryName);
			logger.debug(String.format("Category  %s removed", categoryName));
			return ResponseEntity.accepted().build();
		}catch(EmptyResultDataAccessException ex) {
			logger.error(String.format("Category %s not found", categoryName));
			return new ResponseEntity<GameError>(new GameError(HttpStatus.NOT_FOUND,"Category not found",String.format("/categories/%s", categoryName),ex), HttpStatus.NOT_FOUND);
		}
	}
	
}
