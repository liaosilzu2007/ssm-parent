package com.lzumetal.ssm.druid.service;

import com.lzumetal.ssm.druid.entity.Area;
import com.lzumetal.ssm.druid.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-31
 */
@Service
public class AreaService {

    @Resource
    private AreaMapper areaMapper;

    public void updateCode(List<String> areaCodes, String prefix) {
        List<Area> areas = areaMapper.selectAll();
        List<Area> areaList = new ArrayList<>();
        for (Area outerArea : areas) {
            boolean flag = true;
            for (Area innerArea : areas) {
                if (Objects.equals(innerArea.getFcode(), outerArea.getFpcode())) {
                    outerArea.getAreas().add(innerArea);
                    flag = false;
                }
            }
            if (flag) {
                areaList.add(outerArea);
            }
        }
    }
}
