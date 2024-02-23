package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.MenuDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("foodcatalogue")
@CrossOrigin
public class FoodCatalogueController {
    @Autowired
    FoodCatalogueService foodCatalogueService;
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
      FoodItemDTO saveFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(saveFoodItem, HttpStatus.CREATED);
    }
    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId){
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchRestaurantDetailsWithFoodMenu(restaurantId);
        return new ResponseEntity<>(foodCataloguePage,HttpStatus.OK);
    }
    @GetMapping("/hello")
    public String getBame(){
        return "dinesh";
    }
    @PostMapping("/menu")
    public ResponseEntity<MenuDTO>  addMenu(@RequestBody MenuDTO menuDTO){
        MenuDTO saveItem = foodCatalogueService.saveMenu(menuDTO);
        return new  ResponseEntity<>(saveItem,HttpStatus.CREATED);

    }

}
