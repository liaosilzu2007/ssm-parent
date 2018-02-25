package com.lzumetal.ssm.druid.controller;

import com.lzumetal.ssm.druid.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-31
 */
@RestController
@RequestMapping(value = "/area")
public class AreaController {

    private static final Logger log = LoggerFactory.getLogger(AreaController.class);

    @Resource
    private AreaService areaService;

    @RequestMapping(value = "/updateCode")
    public void updateCode() {
        List<String> areaCodes = new ArrayList<>();
        areaCodes.add("320000");
        areaCodes.add("330000");
        areaCodes.add("370000");
        areaCodes.add("350000");
        areaCodes.add("360000");
        areaCodes.add("310000");
        areaCodes.add("340000");
        String prefix = "11";
        areaService.updateCode(areaCodes, prefix);
    }

}
