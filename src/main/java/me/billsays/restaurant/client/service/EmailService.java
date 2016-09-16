package me.billsays.restaurant.client.service;

import me.billsays.restaurant.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * 9/14/16, 2016
 *
 * @author mkvitko
 */
@Service
public class EmailService {
    private static final String NAME_TOKEN = "@name@";
    private static final String CONFIRMATION_TOKEN = "@token@";
    private static final String HOST_TOKEN = "@host@";
    private static final String BODY_TEXT_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>EMAIL CONFIRMATION</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <br>Hi, @name@!\n" +
            "    <br>Please go to this link: <a href=\"http://@host@/confirm?token=@token@\">confirmation link</a>.\n" +
            "    <br>To confirm your e-mail on billsays.me.\n" +
            "    <br>\n" +
            "    <br>Best regards,\n" +
            "    <br>Billsays team!\n" +
            "</body>\n" +
            "</html>";

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${email.conformation.host}")
    private String host;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async("workExecutor")
    public void sendConfirmationMail(User user) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmail());
        helper.setFrom(sender);
        helper.setSubject("Confirmation for billsays.me account");
        String body = BODY_TEXT_TEMPLATE
                .replace(NAME_TOKEN, user.getName())
                .replace(CONFIRMATION_TOKEN, user.getToken().getToken())
                .replace(HOST_TOKEN, host);
        helper.setText(body, Boolean.TRUE);
        javaMailSender.send(helper.getMimeMessage());
    }
}
