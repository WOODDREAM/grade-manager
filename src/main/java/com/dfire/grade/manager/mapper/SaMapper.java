package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.SaBean;
import org.springframework.stereotype.Service;

/**
 * User:huangtao
 * Date:2016-02-19
 * description：
 */
@Service
public interface SaMapper {
    void insert(SaBean saBean);
    SaBean queryById(String id);
}
