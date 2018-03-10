package com.lzumetal.mybatispages.service;

import com.lzumetal.mybatispages.dao.UserDao;
import com.lzumetal.mybatispages.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> list(int pageNo, int pageSize) {
        return userDao.list();
    }
}
