package com.foodniche.rest.services.email;

import java.util.Date;

/**
 * Mail message entity
 *
 * @author Alexey Borschenko
 * @since 01 Dec 2015
 */
public class Mail {

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String templateName;

    private String contentType;

    public Mail() {
        contentType = "text/html";
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public Date getMailSendDate() {
        return new Date();
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    @Override
    public String
    toString() {
        final StringBuilder sb = new StringBuilder("Mail{");
        sb.append("mailFrom='").append(mailFrom).append('\'');
        sb.append(", mailTo='").append(mailTo).append('\'');
        sb.append(", mailCc='").append(mailCc).append('\'');
        sb.append(", mailBcc='").append(mailBcc).append('\'');
        sb.append(", mailSubject='").append(mailSubject).append('\'');
        sb.append(", mailContent='").append(mailContent).append('\'');
        sb.append(", templateName='").append(templateName).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
