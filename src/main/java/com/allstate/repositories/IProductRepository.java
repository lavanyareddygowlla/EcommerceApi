package com.allstate.repositories;

import com.allstate.entities.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by localadmin on 17/01/17.
 */


public interface IProductRepository extends CrudRepository<Product, Integer>{

    public Product findByNameOrStockNumber(String name,String stockNumber);
}
