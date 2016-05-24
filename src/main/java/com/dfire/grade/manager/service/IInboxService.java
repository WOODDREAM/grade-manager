package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.Email;
import com.dfire.grade.manager.vo.JsonResult;

/**
 * Created by Yin on 2016/5/22.
 */
public interface IInboxService {
    JsonResult insertEmail(String body, String subject, String teacherId, String studentId, String address, String fileName) throws Exception;

    JsonResult makeReaded(String id) throws Exception;

    JsonResult deleteEmail(String id) throws Exception;

    JsonResult findEmail(Email email) throws Exception;
    JsonResult selectUnreadCount(Email email) throws Exception;
}
