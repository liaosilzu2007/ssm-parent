package com.lzumetal.mybatispages.service;

import com.lzumetal.mybatispages.dao.UserDao;
import com.lzumetal.mybatispages.entity.User;
import com.lzumetal.mybatispages.entity.UserParam;
import com.lzumetal.mybatispages.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-03-10
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Map<Long, User> getById(Long id) {
        return userDao.getById(id);
    }

    public List<User> list(Long id) {
        return userDao.list(id);
    }

    public Pagination<User> getByPage(String createdStart, int currentPage, int pageSize) {
        UserParam userParam = new UserParam();
        userParam.setCreatedStart(createdStart);
        return userDao.getByPage(userParam, currentPage, pageSize);
    }

}
