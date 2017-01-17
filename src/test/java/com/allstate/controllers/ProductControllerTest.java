package com.allstate.controllers;

import com.allstate.entities.Product;
import com.allstate.services.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    private Product product;

    @MockBean
    private ProductService service;


    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(5);
        product.setName("headphones");
        product.setStockNumber("abcd");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateProduct() throws Exception {
        given(this.service.createProduct(Mockito.any(Product.class)))
                .willReturn(product);

        MockHttpServletRequestBuilder request = post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"new earphones\" ,\"stockNumber\": \"xyz\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.name", is("headphones")));
    }

//    @Test
//    public void findAllProduct() throws Exception {
//
//    }
//
//    @Test
//    public void findProductById() throws Exception {
//
//    }
//
//    @Test
//    public void findProductByNameOrStockNumber() throws Exception {
//
//    }
//
//    @Test
//    public void deleteProductById() throws Exception {
//
//    }
//
//    @Test
//    public void updateProductById() throws Exception {
//
//    }
//
//    @Test
//    public void findAverageRating() throws Exception {
//
//    }
//
//    @Test
//    public void findCountOfReviews() throws Exception {
//
//    }
//
//    @Test
//    public void findQuantityOfProducts() throws Exception {
//
//    }
//
//    @Test
//    public void findAllRestricted() throws Exception {
//
//    }

}