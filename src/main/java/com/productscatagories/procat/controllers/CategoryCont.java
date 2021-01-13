package com.productscatagories.procat.controllers;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productscatagories.procat.models.Category;
import com.productscatagories.procat.services.ProdCatServ;

@Controller
public class CategoryCont {
    private final ProdCatServ prodCatServ;

    public CategoryCont(ProdCatServ prodCatServ) {
        this.prodCatServ = prodCatServ;
    }

    @RequestMapping("/categories/new")
    public String newCatPage(@ModelAttribute("categoryObject") Category category) {
        return "ProductsAndCategories/newcategory.jsp";
    }

    @RequestMapping("/addcategory")
    public String addCategory(@Valid @ModelAttribute("categoryObject") Category category, BindingResult result) {
        if(result.hasErrors()) {
            return "/categories/new";
        }
        else {
            prodCatServ.addCat(category);
            return "redirect:/categories/new";
        }
    }
}