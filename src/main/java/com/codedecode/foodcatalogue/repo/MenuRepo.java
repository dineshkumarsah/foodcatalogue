package com.codedecode.foodcatalogue.repo;

import com.codedecode.foodcatalogue.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepo extends JpaRepository<Menu,Long> {
}
