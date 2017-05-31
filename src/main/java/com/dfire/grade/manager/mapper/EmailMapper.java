package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Email;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yin on 2016/5/22.
 */
@Component
public interface EmailMapper {
    void insertEmail(Email email) throws Exception;

    void updateReaded(String id) throws Exception;

    void deleteById(String id) throws Exception;

    List<Email> selectByCondition(Email email) throws Exception;

    int selectUnreadCount(Email email) throws Exception;
}
