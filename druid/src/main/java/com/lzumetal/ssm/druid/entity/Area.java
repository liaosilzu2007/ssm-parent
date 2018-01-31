package com.lzumetal.ssm.druid.entity;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private String fcode;

    private String fpcode;

    private String fname;

    private String ffullname;

    private List<Area> areas = new ArrayList<>();

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode == null ? null : fcode.trim();
    }

    public String getFpcode() {
        return fpcode;
    }

    public void setFpcode(String fpcode) {
        this.fpcode = fpcode == null ? null : fpcode.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFfullname() {
        return ffullname;
    }

    public void setFfullname(String ffullname) {
        this.ffullname = ffullname == null ? null : ffullname.trim();
    }
}