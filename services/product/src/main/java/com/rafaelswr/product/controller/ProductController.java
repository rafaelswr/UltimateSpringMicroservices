package com.rafaelswr.product.controller;

import com.rafaelswr.product.product.ProductRequest;
import com.rafaelswr.product.product.ProductPurchaseRequest;
import com.rafaelswr.product.product.ProductPurchaseResponse;
import com.rafaelswr.product.product.ProductResponse;
import com.rafaelswr.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewProduct(@RequestBody @Valid ProductRequest productRequest){
        return new ResponseEntity<>(productService.createNewProduct(productRequest), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> productPurchaseRequests){
        return new ResponseEntity<>(productService.purchaseProducts(productPurchaseRequests), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id ){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

}

