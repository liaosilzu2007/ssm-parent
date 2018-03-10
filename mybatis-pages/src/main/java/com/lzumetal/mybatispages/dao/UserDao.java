package com.lzumetal.mybatispages.dao;

import com.lzumetal.mybatispages.entity.po.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @Author liaosi
 * @Date 2018-03-10
 */
@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    @MapKey("id")
    public Map<Long, User> getById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return sqlSession.selectOne("User.select", params);
    }

    public List<User> list() {

        return sqlSession.selectList("User.list");
    }


}
