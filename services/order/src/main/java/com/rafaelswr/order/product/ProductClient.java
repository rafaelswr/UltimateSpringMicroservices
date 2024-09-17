package com.rafaelswr.order.product;

import com.rafaelswr.order.exception.BusinessException.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Service
@RequiredArgsConstructor
public class ProductClient {


    @Value("${application.config.product")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){
       HttpHeaders headers = new HttpHeaders();
       headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
       HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
       ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<List<PurchaseResponse>>() {};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl+"/purchase", POST, requestEntity, responseType
        );

        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occured while processing the products" +
                    "purchase");
        }

        return responseEntity.getBody();
    }
}
