package com.rafaelswr.notification.kafka;

import com.rafaelswr.notification.email.EmailService;
import com.rafaelswr.notification.kafka.order.OrderConfirmation;
import com.rafaelswr.notification.kafka.payment.PaymentConfirmation;
import com.rafaelswr.notification.kafka.payment.PaymentMethod;
import com.rafaelswr.notification.notification.Notification;
import com.rafaelswr.notification.notification.NotificationRepository;
import com.rafaelswr.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(topics = "payment-confirmation")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming payment from payment-topic:: %s",paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // todo send email
      

    }

    @KafkaListener(topics = "orderNotification-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation){
        log.info(String.format("Consuming order from topic:: %s",orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo send email
    }


}

