package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.MenuDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.entity.Menu;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.mapper.MenuMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;
import com.codedecode.foodcatalogue.repo.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemMapper mapper = new FoodItemMapper();
       FoodItem savedFoodItem =  foodItemRepo.save(mapper.mapToFoodItem(foodItemDTO));

      FoodItemDTO dto =  mapper.mapToFoodItemDTO(savedFoodItem);

      dto.setId(savedFoodItem.getId());
      dto.setItemName(savedFoodItem.getItemName());
      dto.setItemDescription(savedFoodItem.getItemDescription());
      dto.setIsVeg(savedFoodItem.getIsVeg());
      dto.setPrice(savedFoodItem.getPrice());
      dto.setRestaurantId(savedFoodItem.getRestaurantId());

       return dto;
    }

    public FoodCataloguePage fetchRestaurantDetailsWithFoodMenu(Integer restaurantId) {
        //food item list
        //restaurantDetails
       List<FoodItem> foodItemList= fetchItemList(restaurantId);
       Restaurant restaurant =  fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList,restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
       FoodCataloguePage foodCataloguePage = new  FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
      return restTemplate.getForObject("http://RESTAURANT2-SERVICE/restaurant/"+restaurantId, Restaurant.class);
    }
    private List<FoodItem> fetchItemList(Integer restaurantId) {
        List<FoodItem> foodItemList;
      if (restaurantId == null || restaurantId <= 0) {
        throw new IllegalArgumentException("Invalid restaurantId: " + restaurantId);
      }else{
        foodItemList  =   foodItemRepo.findByRestaurantId(restaurantId);
      }
      if(foodItemList ==null){
          return Collections.emptyList();
      }
        return foodItemList;
    }

    public MenuDTO saveMenu(MenuDTO menuDTO) {
        MenuMapper mapper = new MenuMapper();
        Menu savedMenu = menuRepo.save(mapper.mapToMenu(menuDTO));
        return mapper.mappToMenuDto(savedMenu);
    }
}
