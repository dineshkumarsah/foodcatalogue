package com.codedecode.foodcatalogue.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private int id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private Number price;
    private Integer restaurantId;
    private Integer quantity;

    public FoodItemDTO(int id, String itemName, String itemDescription, Boolean isVeg, Number price, Integer restaurantId) {

    }
}
