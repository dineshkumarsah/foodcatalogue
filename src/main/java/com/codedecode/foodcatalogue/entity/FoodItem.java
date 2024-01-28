package com.codedecode.foodcatalogue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity

@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String itemName;
   private String itemDescription;
   private Boolean isVeg;
   private Number price;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
   private Integer restaurantId;
}
