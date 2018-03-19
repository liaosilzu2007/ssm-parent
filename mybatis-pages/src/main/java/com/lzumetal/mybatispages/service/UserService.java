package com.lzumetal.mybatispages.service;

import com.lzumetal.mybatispages.dao.UserDao;
import com.lzumetal.mybatispages.entity.User;
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

    public List<User> getByPage(String queryDay, int currentPage, int pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("queryDay", queryDay);
        paramMap.put("currentPage", currentPage);
        paramMap.put("pageSize", pageSize);
        return userDao.getByPage(paramMap);

    }

}
