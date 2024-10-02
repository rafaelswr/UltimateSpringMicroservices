package com.rafaelswr.notification.email;

import com.rafaelswr.notification.kafka.order.Product;
import com.rafaelswr.notification.kafka.payment.PaymentMethod;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sentPaymentSuccessEmail(String destinationEmail,
                                        PaymentMethod paymentMethod,
                                        String customerName,
                                        BigDecimal amount,
                                        String orderReference) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("rafaels.borges91@gmail.com");

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        /// variables to use in thymeleaf template
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("paymentMethod", paymentMethod);
        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try{
            /// process the template using the file and inject variables
            String htmlTemplate = templateEngine.process(templateName, context);

            ///html email content
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);

            mailSender.send(message);
            log.info("email was sent");

        }catch (MessagingException e){
            log.warn("WARNING---CANNOT SEND EMAIL TO {}", destinationEmail);
        }
    }

    @Async
    public void sentOrderSuccessEmail(String destinationEmail,
                                      String customerName, PaymentMethod paymentMethod,
                                      BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {

        MimeMessage emailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(emailMessage,MimeMessageHelper.MULTIPART_MODE_RELATED ,StandardCharsets.UTF_8.name());
        messageHelper.setFrom("rafaels.borges91@gmail.com");

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("paymentMethod", paymentMethod);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(emailMessage);
            log.info("email was sent");
        }catch (MessagingException e){
            log.warn("ERROR ON PROCESSING ORDER NOTIFICATION EMAIL ");
        }

    }



}

