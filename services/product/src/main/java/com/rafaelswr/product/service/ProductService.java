package com.rafaelswr.product.service;

import com.rafaelswr.product.exception.ProductPurchaseException;
import com.rafaelswr.product.product.ProductRequest;
import com.rafaelswr.product.product.ProductPurchaseRequest;
import com.rafaelswr.product.product.ProductPurchaseResponse;
import com.rafaelswr.product.product.ProductResponse;
import com.rafaelswr.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public String createNewProduct(ProductRequest productRequest) {
        productRepository.save(productMapper.toProduct(productRequest));
        return "New Product Added";
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse).orElseThrow(()->
                        new EntityNotFoundException("Product By Id no found")
                );
    }


    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests) {

        var productsByIds = productPurchaseRequests.stream()
                .map(ProductPurchaseRequest::product_id).toList();

        var storeProducts = productRepository.findAllByIdInOrderById(productsByIds);

        if(storeProducts.size() != productsByIds.size()){
            throw new ProductPurchaseException("One or more products does not exist!");
        }

        var storeRequest = productPurchaseRequests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::product_id))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storeRequest.size(); i++){
            var product = storeProducts.get(i);
            var productRequestQuantity = storeRequest.get(i).quantity();
            if(productRequestQuantity > product.getAvailableQuantity()){
                throw new ProductPurchaseException("Request Quantity is over product stock!");
            }
            var availableQuantity = product.getAvailableQuantity() - productRequestQuantity;
            product.setAvailableQuantity(availableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product,productRequestQuantity));
        }

        return purchasedProducts;

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());

    }
}
