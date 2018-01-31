package com.lzumetal.ssm.druid.mapper;


import com.lzumetal.ssm.druid.entity.Area;

import java.util.List;

public interface AreaMapper {
    int insert(Area record);

    List<Area> selectAll();

}