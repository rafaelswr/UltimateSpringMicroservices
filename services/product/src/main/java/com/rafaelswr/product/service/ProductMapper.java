package com.rafaelswr.product.service;

import com.rafaelswr.product.product.*;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder()
                        .category_id(productRequest.categoryId())
                        .build())
                .build();
    }
    public ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .availableQuantity(product.getAvailableQuantity())
                    .categoryId(product.getCategory().getCategory_id())
                    .categoryName(product.getName())
                    .categoryDescription(product.getDescription())
                .build();
    }

    public ProductPurchaseResponse  toProductPurchaseResponse (Product product, Integer quanyityRequest){
        return ProductPurchaseResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productId(product.getId())
                .requestQuantity(quanyityRequest)
                .build();
    }

}
