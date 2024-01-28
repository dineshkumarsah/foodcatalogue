package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;
    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemMapper mapper = new FoodItemMapper();
       FoodItem savedFoodItem =  foodItemRepo.save(mapper.mapToFoodItem(foodItemDTO));
       return mapper.mapToFoodItemDTO(savedFoodItem);
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
      return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/"+ restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
