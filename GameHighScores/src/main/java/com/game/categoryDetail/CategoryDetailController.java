package com.game.categoryDetail;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.category.CategoryController;
import com.game.exceptions.GameError;


@RestController
public class CategoryDetailController {

	@Autowired
	CategoryDetailService categoryDetailService;
	

	private static final Logger logger = Logger.getLogger(CategoryController.class);
	
	
	@GetMapping("/categories/{categoryName}")
	public List<CategoryDetail> getTopPlayerByCategory(@PathVariable String categoryName){
		return categoryDetailService.getAllTopPlayersByCategory(categoryName);
	}
	
	@GetMapping("/categorydetails/{categoryDetailId}")
	public CategoryDetail getCategoryDetail(@PathVariable int categoryDetailId) {
		return categoryDetailService.getCategoryDetail(categoryDetailId);
	}
	
	@GetMapping("/players/{userName}")
	public List<CategoryDetail> getCategorydetailsByPlayer(@PathVariable String userName) throws EntityNotFoundException{
		return categoryDetailService.getCategoryDetailByPlayer(userName);
	}
	
	@DeleteMapping("/categorydetails/{categoryDetailId}")
	public ResponseEntity<?> deleteCategoryDetail(@PathVariable int categoryDetailId) {
		try {
			categoryDetailService.removeCategoryDetail(categoryDetailId);
			logger.debug(String.format("CategoryDetail %s removed ", categoryDetailId));
			return ResponseEntity.accepted().build();
		}catch(EmptyResultDataAccessException ex) {
			logger.error(String.format("CategoryDetail %s not found", categoryDetailId));
			return new ResponseEntity<GameError>(new GameError(HttpStatus.NOT_FOUND,"CategoryDetail not found",String.format("/categorydetail/%s", categoryDetailId),ex), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/categorydetails")
	public ResponseEntity<?> updateCategoryDetail(@RequestBody CategoryDetail categoryDetail) {
		try {
			categoryDetailService.updateCategoryDetail(categoryDetail);
			logger.debug(String.format("CategoryDetail %s updated",categoryDetail));
			return new ResponseEntity<CategoryDetail>(categoryDetail, HttpStatus.ACCEPTED);
		}catch(IdentifierGenerationException ex) {
			logger.error("Invalid categoryDetail");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid categoryDetail","/categoryDetail", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(DataIntegrityViolationException ex) {
			logger.error("Invalid category, could not find a valid categoryDetail in the body request");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid categoryDetail, could not find a valid categoryDetail in the body request","/categoryDetail", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/categorydetails")
	public ResponseEntity<?> addCategoryDetail(@RequestBody CategoryDetail categoryDetail) {
		try {
			categoryDetailService.addCategoryDetail(categoryDetail);
			logger.debug(String.format("CategoryDetail %s added",categoryDetail));
			return new ResponseEntity<CategoryDetail>(categoryDetail, HttpStatus.CREATED);
		}catch(IdentifierGenerationException ex) {
			logger.error("Invalid categoryDetail");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid categoryDetail","/categoryDetail", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(DataIntegrityViolationException ex) {
			logger.error("Invalid categoryDetail, could not find a valid categoryDetail in the body requestd");
			return new ResponseEntity<GameError>(new GameError(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid categoryDetail, could not find a valid categoryDetail in the body request ","/categoryDetail", ex), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
}
