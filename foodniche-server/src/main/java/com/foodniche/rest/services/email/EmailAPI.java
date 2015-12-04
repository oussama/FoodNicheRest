package com.foodniche.rest.services.email;

import com.foodniche.db.entities.Users;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Email API
 *
 * @author Alexey Borschenko
 * @since 01 Dec 2015
 */
@Component("emailAPI")
public class EmailAPI {

    static Logger logger = LoggerFactory.getLogger(EmailAPI.class);

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private String from;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Sends mail message using entity data and provided parameters map (for velocity template)
     *
     * @param mail                 mail entity
     * @param contentParametersMap parameters map for velocity template
     */
    public void sendMail(Mail mail, Map<String, String> contentParametersMap) {
        logger.debug("Sending mail: {}; parameters: {}", mail, contentParametersMap);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());

        Template template = velocityEngine.getTemplate("./email/" + mail.getTemplateName());

        VelocityContext velocityContext = new VelocityContext();
        contentParametersMap.forEach(velocityContext::put);

        StringWriter stringWriter = new StringWriter();

        template.merge(velocityContext, stringWriter);

        message.setText(stringWriter.toString());

        mailSender.send(message);
    }

    /**
     * Sends user registration email
     *
     * @param userInfo user info
     */
    public void sendUserRegistrationMail(Users userInfo) {
        logger.debug("Sending registration email: {}", userInfo);

        Mail mail = new Mail();
        mail.setMailFrom(from);
        mail.setMailTo("Niyioyelade@yahoo.com"); //@TODO needs to be replaced by user email!!!!
        mail.setMailSubject("FoodNiche Registration");
        mail.setTemplateName("user_registration.vm");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", userInfo.getFirstname());
        parameters.put("lastName", userInfo.getLastname());

        sendMail(mail, parameters);
    }
}
