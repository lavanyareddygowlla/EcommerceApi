package com.allstate.services;


import com.allstate.entities.Product;
import com.allstate.repositories.IProductRepository;
import com.sun.javaws.progress.PreloaderDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        return this.productRepository.save(product);
    }

    public Iterable<Product> findAllProduct(){
        return this.productRepository.findAll();
    }

    public Product findProductById(int id){
        return this.productRepository.findOne(id);
    }

    public Product findProductByNameOrStockNumber(String name, String stockNumber){
        return this.productRepository.findByNameOrStockNumber(name,stockNumber);
    }

    public void deleteProductById(int id){
         this.productRepository.delete(id);
    }

    public Product updateProductById(int id,Product product){
        Product newProduct = this.productRepository.findOne(id);
        newProduct.setName(product.getName());
        newProduct.setStockNumber(product.getStockNumber());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setActualPrice(product.getActualPrice());
        newProduct.setDiscount(product.getDiscount());
        newProduct.setRating(product.getRating());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setRestricted(product.getRestricted());
        newProduct.setNumberOfReview(product.getNumberOfReview());

        return this.productRepository.save(newProduct);


    }

    public double findAverageRating(){
        ArrayList<Product> products = (ArrayList<Product>) this.productRepository.findAll();
        double avg = products.stream().map((item) -> item.getRating()==null ? 0:item.getRating()).reduce((result, i) -> result + i).get();
        return Math.round((avg/products.size())*100.0)/100.0;
    }
    public int findCountOfReviews(){
        ArrayList<Product> products = (ArrayList<Product>) this.productRepository.findAll();
        return products.stream().map((item) -> item.getNumberOfReview()==null ? 0:item.getNumberOfReview()).reduce((result, i) -> result + i).get();
    }


    public int findQuantityOfProducts() {
        ArrayList<Product> products = (ArrayList<Product>) this.productRepository.findAll();
        return products.stream().map((item) -> item.getQuantity()==null ? 0:item.getQuantity()).reduce((result, i) -> result + i).get();
    }

    public List<Product> findAllRestricted(){
        List<Product> products = (List<Product>) this.productRepository.findAll();
        List<Product> result=products.stream()
                .filter(product -> product.getRestricted()==null? false :product.getRestricted()== true)
                .collect(Collectors.toList());
        return result;
    }
}
