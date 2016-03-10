package com.dfire.grade.manager.configInfo;

/**
 * User:huangtao
 * Date:2016-03-10
 * description：
 */
public class MailConfiguration {
    private String formHost;
    private String from;
    private String mailAuth;
    private String mailPort;
    private String mailSmptHost;
    private String mailSmptAuth;
    private String mailCertificate;

    public String getFormHost() {
        return formHost;
    }

    public void setFormHost(String formHost) {
        this.formHost = formHost;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMailAuth() {
        return mailAuth;
    }

    public void setMailAuth(String mailAuth) {
        this.mailAuth = mailAuth;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailSmptHost() {
        return mailSmptHost;
    }

    public void setMailSmptHost(String mailSmptHost) {
        this.mailSmptHost = mailSmptHost;
    }

    public String getMailSmptAuth() {
        return mailSmptAuth;
    }

    public void setMailSmptAuth(String mailSmptAuth) {
        this.mailSmptAuth = mailSmptAuth;
    }

    public String getMailCertificate() {
        return mailCertificate;
    }

    public void setMailCertificate(String mailCertificate) {
        this.mailCertificate = mailCertificate;
    }
}
