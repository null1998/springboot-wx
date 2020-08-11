package com.laoh.core.dao;

import com.laoh.core.entity.WxMessageStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hyd
 * @date 2020/8/9 10:45
 */
@Mapper
public interface  WxMessageStatusDao {
    WxMessageStatus selectWxMessageStatus(String mkey);
    void insertWxMessageStatus(@Param("mkey") String mkey, @Param("status") String status, @Param("result") Object result, @Param("requestCount") Integer requestCount);
}
