package com.rafaelswr.order.exception.BusinessException;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

   private final  String message;

   public BusinessException(String message) {
       this.message = message;
   }


}
