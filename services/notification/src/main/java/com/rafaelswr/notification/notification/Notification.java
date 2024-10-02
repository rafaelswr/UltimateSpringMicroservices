package com.rafaelswr.notification.notification;

import com.rafaelswr.notification.kafka.order.OrderConfirmation;
import com.rafaelswr.notification.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "notification")
public class Notification {

    @Id
    private String id;

    private NotificationType notificationType;
    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;


}
