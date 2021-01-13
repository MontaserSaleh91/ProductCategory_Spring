package com.productscatagories.procat.controllers;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productscatagories.procat.models.Category;
import com.productscatagories.procat.models.CategoryProduct;
import com.productscatagories.procat.models.Product;
import com.productscatagories.procat.services.ProdCatServ;

@Controller
public class ProductCont {
    private final ProdCatServ prodCatServ;

    public ProductCont(ProdCatServ prodCatServ) {
        this.prodCatServ = prodCatServ;
    }

    @RequestMapping("/products/new")
    public String newProdPage(@ModelAttribute("productObject") Product product) {
        return "ProductsAndCategories/newproduct.jsp";
    }

    @RequestMapping("/addproduct")
    public String addProduct(@Valid @ModelAttribute("productObject") Product product, BindingResult result) {
        if(result.hasErrors()) {
            return "ProductsAndCategories/newproduct.jsp";
        }
        else {
            prodCatServ.addProd(product);
            return "redirect:/products/new";
        }
    }

    @RequestMapping("products/{prodid}")
    public String showProduct(@PathVariable("prodid") Long prodid, @ModelAttribute("categoryProductObj") CategoryProduct categoryProduct, Model model) {
        Product product = prodCatServ.singleProd(prodid);
        model.addAttribute("product", product);
        model.addAttribute("categories", prodCatServ.availableCategoriesForProduct(product));
        return "ProductsAndCategories/showproduct.jsp";
    }

    @RequestMapping("/attachcategory")
    public String attachCategory(@ModelAttribute("categoryProductObj") CategoryProduct categoryProduct) {
        System.out.println(categoryProduct.getCategory().getName());
        System.out.println(categoryProduct.getProduct().getName());
        prodCatServ.addCatToProduct(categoryProduct);
        return "redirect:/products/" + categoryProduct.getProduct().getId();
    }

    @RequestMapping("categories/{catid}")
    public String showCategory(@PathVariable("catid") Long catid, @ModelAttribute("categoryProductObj") CategoryProduct categoryProduct, Model model) {
        Category category = prodCatServ.singleCat(catid);
        model.addAttribute("category", category);
        model.addAttribute("products", prodCatServ.availableProductsForCategory(category));
        return "ProductsAndCategories/showcategory.jsp";
    }

    @RequestMapping("/attachproduct")
    public String attachProduct(@ModelAttribute("categoryProductObj") CategoryProduct categoryProduct) {
        System.out.println(categoryProduct.getCategory().getName());
        System.out.println(categoryProduct.getProduct().getName());
        prodCatServ.addProdToCategory(categoryProduct);
        return "redirect:/categories/" + categoryProduct.getCategory().getId();
    }
}