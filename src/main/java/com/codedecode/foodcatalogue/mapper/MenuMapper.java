package com.codedecode.foodcatalogue.mapper;

import com.codedecode.foodcatalogue.dto.MenuDTO;
import com.codedecode.foodcatalogue.entity.Menu;

public class MenuMapper {
    public static MenuDTO mappToMenuDto(Menu menu){
        return new MenuDTO(
                menu.getId(),
                menu.getName()
        );
    }

    public static Menu mapToMenu(MenuDTO dto){
        return new Menu(
                dto.getId(),
                dto.getName()
        );

    }
}
