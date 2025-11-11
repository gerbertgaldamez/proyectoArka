package com.enyoi.arkana.notificacion.config;

import com.enyoi.arkana.notificacion.adapters.ConsoleSmsSender;
import com.enyoi.arkana.notificacion.adapters.SmtpEmailSender;
import com.enyoi.arkana.notificacion.ports.EmailSender;
import com.enyoi.arkana.notificacion.ports.SmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeanConfig {

    @Bean
    public EmailSender emailSender(JavaMailSender javaMailSender) {

        return new SmtpEmailSender(javaMailSender);
    }

    @Bean
    public SmsSender smsSender() {

        return new ConsoleSmsSender();
    }

    @Bean
    public JavaMailSender javaMailSender() {

        return new JavaMailSenderImpl(); // Se configura via application.yml
    }
}
