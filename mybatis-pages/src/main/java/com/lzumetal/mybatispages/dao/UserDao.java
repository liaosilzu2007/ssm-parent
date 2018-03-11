package com.lzumetal.mybatispages.dao;

import com.lzumetal.mybatispages.entity.po.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
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
    private SqlSessionTemplate sqlSession;

    /*
    将查询出来的记过封装成一个map，map的key可以指定为某个字段，比如id；map的value是查询出来的对象
     */
    public Map<Long, User> getById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return sqlSession.selectMap("User.select", params, "id");
    }

    public List<User> list(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return sqlSession.selectList("User.list");
    }


}
