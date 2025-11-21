package com.mtf.edumarine.controller;

import com.mtf.edumarine.dto.MenuItem;
import com.mtf.edumarine.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private MenuService menuService;

    @ModelAttribute("menuItems")
    public List<MenuItem> menuItems() {
        return menuService.getMenuList();
    }

}
