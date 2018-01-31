package com.game.categoryDetail;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.category.Category;
import com.game.player.Player;


@Service
public class CategoryDetailService  {
	
	@Autowired 
	CategoryDetailRepository categoryDetailRepository; 
	public List<CategoryDetail> getAllTopPlayersByCategory(String categoryName){
		return categoryDetailRepository.findByCategoryCategoryName(categoryName)
								.stream()
								.sorted((c1, c2) -> Long.compare(c2.getScore(), c1.getScore()))
								.limit(10).collect(Collectors.toList());					
	}
	public List<CategoryDetail> getCategoryDetailByPlayer(String userName){
		return categoryDetailRepository.findByPlayerUserName(userName);
	}
	public CategoryDetail getCategoryDetail(int categoryDetailId) {
		return categoryDetailRepository.findOne(categoryDetailId);
	}
	public void removeCategoryDetail(int categoryDetailId) {
		Player player = categoryDetailRepository.findOne(categoryDetailId).getPlayer();
		categoryDetailRepository.delete(categoryDetailId);
		updateOverall(player);
	}
	public void updateCategoryDetail(CategoryDetail categoryDetail) {
		categoryDetailRepository.save(categoryDetail);
		updateOverall(categoryDetail.getPlayer());
	}
	public void addCategoryDetail(CategoryDetail categoryDetail) {
		categoryDetailRepository.save(categoryDetail);
		updateOverall(categoryDetail.getPlayer());
	}
	
	//Actualiza la categoria overall cuando hay un save o un delete.
	public void updateOverall(Player player) {
		Optional<Integer> categoryDetailId = categoryDetailRepository.findByPlayerUserName(player.getUserName())
				.stream()
				.filter(c ->  c.getCategory().getCategoryName().equals("overall"))
				.map(CategoryDetail::getCategoryDetailId).findFirst();
		
		Optional<Integer> level = categoryDetailRepository.findByPlayerUserName(player.getUserName())
								.stream()
								.filter(c -> ! c.getCategory().getCategoryName().equals("overall"))
								.map(CategoryDetail::getLevel).reduce(Integer::sum);
		
		Optional<Long> score = categoryDetailRepository.findByPlayerUserName(player.getUserName())
				.stream()
				.filter(c -> ! c.getCategory().getCategoryName().equals("overall"))
				.map(CategoryDetail::getScore).reduce(Long::sum);
		
		if(score.isPresent() && level.isPresent()) {
			if (categoryDetailId.isPresent()) {
				categoryDetailRepository.save(new CategoryDetail(categoryDetailId.get(),new Category("overall"), player, level.get(), score.get() ));
			}else {	
				categoryDetailRepository.save(new CategoryDetail(new Category("overall"), player, level.get(), score.get() ));
			}
		}else {
			if (categoryDetailId.isPresent())
				categoryDetailRepository.delete(categoryDetailId.get());
		}
	}
}
