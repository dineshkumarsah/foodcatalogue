package com.codedecode.foodcatalogue.mapper;

import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;

public class FoodItemMapper {
public static FoodItemDTO mapToFoodItemDTO(FoodItem foodItem){
    return new FoodItemDTO(
            foodItem.getId(),
            foodItem.getItemName(),
            foodItem.getItemDescription(),
            foodItem.getIsVeg(),
            foodItem.getPrice(),
            foodItem.getRestaurantId()
    );
}
public static FoodItem mapToFoodItem(FoodItemDTO dto){
    return new FoodItem(
          dto.getId(),
          dto.getItemName(),
          dto.getItemDescription(),
          dto.getIsVeg(),
          dto.getPrice(),
          dto.getRestaurantId()
    );
}
}
