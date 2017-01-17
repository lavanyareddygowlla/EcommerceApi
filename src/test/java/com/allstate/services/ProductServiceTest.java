package com.allstate.services;

import com.allstate.entities.Product;
import org.assertj.core.util.Lists;
import org.hibernate.annotations.SourceType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 17/01/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateProduct() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");

        Product result = this.productService.createProduct(p);
        assertEquals(result.getId(),2);

    }

    @Test
    public void shouldFindAllProduct() throws Exception {
        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        p.setActualPrice(21);

        Product result = this.productService.createProduct(p);


        ArrayList<Product> products =(ArrayList<Product>) this.productService.findAllProduct();
        System.out.println("..................."+p.getName()+"****************");
        assertEquals(products.size(),2);

    }

    @Test
    public void shouldFindProductById() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        Product inserted = this.productService.createProduct(p);
        Product result = this.productService.findProductById(2);
        assertNotNull(result);
        assertEquals("ATX432",result.getStockNumber());

    }


    @Test
    public void shouldFindProductByNameOrStockNumber() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        Product inserted = this.productService.createProduct(p);
        Product result=this.productService.findProductByNameOrStockNumber(p.getName(),"ATX432");
        assertNotNull(result);
        assertEquals("ATX432",result.getStockNumber());

    }

    @Test
    public void shouldDeleteProductById() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        Product inserted = this.productService.createProduct(p);
        this.productService.deleteProductById(2);
        Product result= this.productService.findProductById(2);
        assertNull(result);

    }

    @Test
    public void shouldUpdateProductById() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        Product inserted = this.productService.createProduct(p);
        Product productAfterUpdate=new Product();
        productAfterUpdate.setName("Test headphones");
        productAfterUpdate.setStockNumber("ASV4566");
        Product result= this.productService.updateProductById(2,productAfterUpdate);

        assertEquals("Test headphones",result.getName());

    }

    @Test
    public void shouldReturnAverageRatingOfAll() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        p.setRating(3);
        Product inserted = this.productService.createProduct(p);
        Product p2=new Product();
        p2.setName("Test headphones");
        p2.setStockNumber("ASV4566");
        p2.setRating(5);
        Product inserted1= this.productService.createProduct(p2);
        double result= this.productService.findAverageRating();

        assertEquals(2.67,result,0.1);

    }

    @Test
    public void shouldReturnCountOfReview() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        p.setNumberOfReview(3);
        Product inserted = this.productService.createProduct(p);
        Product p2=new Product();
        p2.setName("Test headphones");
        p2.setStockNumber("ASV4566");
        p2.setNumberOfReview(5);
        Product inserted1= this.productService.createProduct(p2);
        int result= this.productService.findCountOfReviews();

        assertEquals(8,result);

    }


    @Test
    public void shouldReturnQuantityOfProducts() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        p.setQuantity(3);
        Product inserted = this.productService.createProduct(p);
        Product p2=new Product();
        p2.setName("Test headphones");
        p2.setStockNumber("ASV4566");
        p2.setQuantity(5);
        Product inserted1= this.productService.createProduct(p2);
        int result= this.productService.findQuantityOfProducts();

        assertEquals(8,result);

    }


    @Test
    public void shouldListRestrictedProducts() throws Exception {

        Product p = new Product();
        p.setName("jbl headphone");
        p.setStockNumber("ATX432");
        p.setQuantity(3);
        p.setRestricted(true);
        Product inserted = this.productService.createProduct(p);
        List<Product> result= this.productService.findAllRestricted();
        assertEquals(1,result.size());

    }

    
}