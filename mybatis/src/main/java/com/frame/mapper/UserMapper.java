package com.frame.mapper;

import com.frame.bean.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();
}