package com.game.categoryDetail;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CategoryDetailRepository extends CrudRepository<CategoryDetail,Integer> {
	public List<CategoryDetail> findByCategoryCategoryName(String categoryName);
	public List<CategoryDetail> findByPlayerUserName(String userName);
}
