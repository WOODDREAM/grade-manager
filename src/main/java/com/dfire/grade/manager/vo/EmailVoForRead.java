package com.dfire.grade.manager.vo;

import com.dfire.grade.manager.bean.Email;

import java.util.List;

/**
 * Created by Yin on 2016/5/22.
 */
public class EmailVoForRead {
    private List<EmailVo> readedEmails;
    private List<EmailVo> unReadEmails;
    private List<EmailVo> sendEmails;
    private List<EmailVo> deletedEmails;
    private List<EmailVo> importantEmails;

    public List<EmailVo> getImportantEmails() {
        return importantEmails;
    }

    public void setImportantEmails(List<EmailVo> importantEmails) {
        this.importantEmails = importantEmails;
    }

    public List<EmailVo> getDeletedEmails() {
        return deletedEmails;
    }

    public void setDeletedEmails(List<EmailVo> deletedEmails) {
        this.deletedEmails = deletedEmails;
    }

    public List<EmailVo> getSendEmails() {
        return sendEmails;
    }

    public void setSendEmails(List<EmailVo> sendEmails) {
        this.sendEmails = sendEmails;
    }

    public List<EmailVo> getReadedEmails() {
        return readedEmails;
    }

    public void setReadedEmails(List<EmailVo> readedEmails) {
        this.readedEmails = readedEmails;
    }

    public List<EmailVo> getUnReadEmails() {
        return unReadEmails;
    }

    public void setUnReadEmails(List<EmailVo> unReadEmails) {
        this.unReadEmails = unReadEmails;
    }
}
