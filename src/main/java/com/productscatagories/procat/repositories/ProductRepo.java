package com.productscatagories.procat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.productscatagories.procat.models.Category;
import com.productscatagories.procat.models.Product;

@Repository
public interface ProductRepo extends CrudRepository <Product, Long> {
    List<Product> findAll();
    List<Product> findByCategoriesNotContains(Category category);
}