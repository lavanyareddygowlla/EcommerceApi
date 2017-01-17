package com.allstate.controllers;

import com.allstate.entities.Product;
import com.allstate.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by localadmin on 17/01/17.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return this.productService.createProduct(product);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public Iterable<Product> findAllProduct(){
        return this.productService.findAllProduct();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findProductById(@PathVariable int id){
        return this.productService.findProductById(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Product findProductByNameOrStockNumber(@RequestParam Map<String,String> query){
        return this.productService.findProductByNameOrStockNumber(query.get("name"),query.get("stockNumber"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProductById(@PathVariable int id){
        this.productService.deleteProductById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProductById(@PathVariable int id, @RequestBody Product product){
       return this.productService.updateProductById(id,product);
    }

    @RequestMapping(value = "/average", method = RequestMethod.GET)
    public Map<String, Double> findAverageRating(){
        Map<String ,Double> map=new HashMap<String ,Double>();
        double  averageResult=this.productService.findAverageRating();
        map.put("Average",averageResult);
        return map;
    }
    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public Map<String, Integer> findCountOfReviews(){
        Map<String ,Integer> map=new HashMap<String ,Integer>();
        int  countOfReviewsResult=this.productService.findCountOfReviews();
        map.put("Reviews",countOfReviewsResult);
        return map;
    }

    @RequestMapping(value = "/quantity", method = RequestMethod.GET)
    public Map<String, Integer> findQuantityOfProducts(){
        Map<String ,Integer> map=new HashMap<String ,Integer>();
        int  result=this.productService.findQuantityOfProducts();
        map.put("Quantity",result);
        return map;
    }

    @RequestMapping(value = "/restrictions", method = RequestMethod.GET)
    public List<Product> findAllRestricted(){
        return this.productService.findAllRestricted();

    }
}
