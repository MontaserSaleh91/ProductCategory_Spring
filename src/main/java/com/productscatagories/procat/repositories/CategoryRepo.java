package com.productscatagories.procat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.productscatagories.procat.models.Category;
import com.productscatagories.procat.models.Product;

@Repository
public interface CategoryRepo extends CrudRepository <Category, Long> {
    List<Category> findAll();
    List<Category> findByProductsNotContains(Product product);
}