package com.enyoi.arkana.notificacion.adapters;

import com.enyoi.arkana.notificacion.ports.EmailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    public SmtpEmailSender(JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    @Override
    public Mono<Void> send(String to, String subject, String body) {
        return Mono.fromRunnable(() -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
